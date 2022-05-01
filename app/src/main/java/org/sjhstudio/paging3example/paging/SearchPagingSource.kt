package org.sjhstudio.paging3example.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.sjhstudio.paging3example.data.remote.UnsplashApi
import org.sjhstudio.paging3example.model.UnsplashImage
import org.sjhstudio.paging3example.util.Constants.ITEMS_PER_PAGE
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val query: String,
    private val unsplashApi: UnsplashApi
): PagingSource<Int, UnsplashImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val curPage = params.key ?: 1

        return try {
            val response = unsplashApi.searchImages(query, curPage, ITEMS_PER_PAGE)
            val endOfPaginationReached = response.images.isEmpty()

            if(response.images.isNotEmpty()) {
                LoadResult.Page(
                    data = response.images,
                    prevKey = if(curPage == 1) null else curPage - 1,
                    nextKey = if(endOfPaginationReached) null else curPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch(e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition
    }

}