package org.sjhstudio.paging3example.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sjhstudio.paging3example.R
import org.sjhstudio.paging3example.databinding.ItemUnsplashImageBinding
import org.sjhstudio.paging3example.model.UnsplashImage

class UnsplashImageAdapter:
    PagingDataAdapter<UnsplashImage, UnsplashImageAdapter.ViewHolder>(IMAGE_COMPARATOR)
{

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = ItemUnsplashImageBinding.bind(itemView)

        fun bind(data: UnsplashImage) {
            Glide.with(itemView)
                .load(data.urls.regularImage)
                .into(binding.photoImg)
            binding.userNameTv.text = data.user.username
            binding.likesTv.text = data.likes.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_unsplash_image, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        item?.let {
            holder.bind(it)
        }
    }

    companion object {

        private val IMAGE_COMPARATOR = object: DiffUtil.ItemCallback<UnsplashImage>() {
            override fun areItemsTheSame(
                oldItem: UnsplashImage,
                newItem: UnsplashImage
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UnsplashImage,
                newItem: UnsplashImage
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}