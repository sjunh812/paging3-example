package org.sjhstudio.paging3example.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import org.sjhstudio.paging3example.data.local.UnsplashDatabase
import org.sjhstudio.paging3example.model.UnsplashImage
import org.sjhstudio.paging3example.model.UnsplashRemoteKey
import org.sjhstudio.paging3example.data.remote.UnsplashApi
import org.sjhstudio.paging3example.util.Constants.ITEMS_PER_PAGE
import java.lang.Exception

@ExperimentalPagingApi
class UnsplashRemoteMediator(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
): RemoteMediator<Int, UnsplashImage>() {

    private val unsplashImageDao = unsplashDatabase.unsplashImageDao()
    private val unsplashRemoteKeyDao = unsplashDatabase.unsplashRemoteKeyDao()

    /**
     * MediatorResult.Success(endOfPaginationReached = false)
     * : 로드가 성공적, 수신된 항목목록이 비어있지 않음.
     * MediatorResult.Success(endOfPaginationReached = true)
     * : 로드가 성공적, 수신된 항목목록이 비어있거나 마지막 페이지 색인인 경우.
     */
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImage>
    ): MediatorResult {
        return try {
            val curPage = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosetToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKey?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKey!=null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKey!=null
                        )
                    nextPage
                }
            }

            val response = unsplashApi.getAllImages(page = curPage, perPage = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()
            val prevPage = if(curPage == 1) null else curPage-1
            val nextPage = if(endOfPaginationReached) null else curPage+1

            unsplashDatabase.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    unsplashImageDao.deleteAllImages()
                    unsplashRemoteKeyDao.deleteAllRemoteKeys()
                }

                // map : 형변환(List<UnsplashImage> -> List<UnsplashRemoteKey>)
                val keys = response.map { unsplashImage ->
                    UnsplashRemoteKey(
                        id = unsplashImage.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                unsplashImageDao.addImages(images = response)
                unsplashRemoteKeyDao.addAllRemoteKeys(remoteKeys = keys)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch(e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosetToCurrentPosition(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKey? {
        return state.anchorPosition?.let { pos ->
            state.closestItemToPosition(pos)?.id?.let { id ->
                unsplashRemoteKeyDao.getRemoteKey(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                unsplashRemoteKeyDao.getRemoteKey(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                unsplashRemoteKeyDao.getRemoteKey(id = unsplashImage.id)
            }
    }

}