package org.sjhstudio.paging3example.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import org.sjhstudio.paging3example.data.local.UnsplashDatabase
import org.sjhstudio.paging3example.model.UnsplashImage
import org.sjhstudio.paging3example.paging.UnsplashRemoteMediator
import org.sjhstudio.paging3example.data.remote.UnsplashApi
import org.sjhstudio.paging3example.util.Constants.ITEMS_PER_PAGE

@ExperimentalPagingApi
class UnsplashRepository(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) {
    /**
     * Paging 사용법
     *
     * 1. RemoteMediator 사용 (현재)
     * : 네트워킹 캐시를 db에 저장해서 이용하는 방법
     *
     * 2. PagingSource 사용
     * : 일반적인 네트워킹을 이용하는 방법
     */
    fun getAllImages(): LiveData<PagingData<UnsplashImage>> {
        // LiveData 및 Flow 사용가능!!
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi,
                unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }

}