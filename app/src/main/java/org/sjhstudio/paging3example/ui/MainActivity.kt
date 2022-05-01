package org.sjhstudio.paging3example.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.sjhstudio.paging3example.R
import org.sjhstudio.paging3example.databinding.ActivityMainBinding
import org.sjhstudio.paging3example.ui.adapter.UnsplashImageAdapter
import org.sjhstudio.paging3example.viewmodel.MainViewModel

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    private val unsplashViewModel: MainViewModel by viewModels()
    private val unsplashImageAdapter by lazy { UnsplashImageAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
        observeUnsplashImages()
    }

    fun setUi() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.unsplashImageRv.apply {
            adapter = unsplashImageAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    fun observeUnsplashImages() {
        unsplashViewModel.unsplashImages.observe(this) {
            unsplashImageAdapter.submitData(lifecycle, it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_search -> {
                // 이미지 검색
                startActivity(Intent(this, SearchActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}