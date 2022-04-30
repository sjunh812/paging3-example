package org.sjhstudio.paging3example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sjhstudio.paging3example.model.UnsplashImage
import org.sjhstudio.paging3example.repository.UnsplashRepository
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class UnsplashViewModel @Inject constructor(
    application: Application,
    unsplashRepository: UnsplashRepository
): AndroidViewModel(application) {

    private var _unsplashImages = unsplashRepository.getAllImages()
        .cachedIn(viewModelScope) as MutableLiveData<PagingData<UnsplashImage>>
    val unsplashImages: LiveData<PagingData<UnsplashImage>>
        get() = _unsplashImages

}