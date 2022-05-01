package org.sjhstudio.paging3example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.sjhstudio.paging3example.R
import org.sjhstudio.paging3example.databinding.ActivitySearchBinding
import org.sjhstudio.paging3example.ui.adapter.UnsplashImageAdapter
import org.sjhstudio.paging3example.viewmodel.SearchViewModel

@ExperimentalPagingApi
@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val binding: ActivitySearchBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_search) }
    private val searchViewModel: SearchViewModel by viewModels()
    private val searchImagesAdapter: UnsplashImageAdapter by lazy { UnsplashImageAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
        observeSearchImages()
    }

    private fun setUi() {
        setSupportActionBar(binding.toolbar)

        binding.searchEt.setOnEditorActionListener { v, actionId, event ->
            // return값이 true면 키보드 내려가지않음.
            // false면 키보드 내려감.
            when(actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    println("xxx Search images : query(${binding.searchEt.text})")
                    searchViewModel.updateQuery(binding.searchEt.text.toString())
                    false
                }
                else -> {
                    false
                }
            }
        }
        binding.searchCancelImg.setOnClickListener { binding.searchEt.setText("") }

        binding.searchImagesRv.apply {
            adapter = searchImagesAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
        }
    }

    private fun observeSearchImages() {
        searchViewModel.searchedImages.observe(this) {
            println("xxx observeSearchImages")
            searchImagesAdapter.submitData(lifecycle, it)
        }
    }
}