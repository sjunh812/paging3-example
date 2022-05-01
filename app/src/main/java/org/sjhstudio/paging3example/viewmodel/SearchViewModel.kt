package org.sjhstudio.paging3example.viewmodel

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sjhstudio.paging3example.model.UnsplashImage
import org.sjhstudio.paging3example.repository.UnsplashRepository
import javax.inject.Inject

/**
 * SearchViewModel : SearchActivity에서 사용되는 뷰모델
 * 검색할 query를 입력하면
 * query값에 따라 그에맞는 unsplash이미지를 불러옴.
 * (switchMap을 이용해 데이터 변환)
 */
@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: UnsplashRepository
): ViewModel() {

    private var _searchQuery = MutableLiveData<String>()

    val searchedImages: LiveData<PagingData<UnsplashImage>> = _searchQuery.switchMap {
        repository.searchImages(it).cachedIn(viewModelScope)
    }

    fun updateQuery(query: String) {
        _searchQuery.value = query
    }

}