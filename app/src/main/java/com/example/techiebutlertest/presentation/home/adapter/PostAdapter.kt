package com.example.techiebutlertest.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.techiebutlertest.R
import com.example.techiebutlertest.databinding.ItemPostBinding
import com.example.techiebutlertest.domain.entities.PostDisplayData

class PostAdapter(
    diffCallback: DiffUtil.ItemCallback<PostDisplayData>,
    private val itemClickCallBack: ItemClickCallBack
) :
    PagingDataAdapter<PostDisplayData, PostAdapter.ModelViewHolder>(
        diffCallback
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val binding: ItemPostBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_post, parent, false
        )
        return ModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        getItem(position)?.let {
            holder.setData(it)
        }
    }

    inner class ModelViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val itemClick = binding.body
            .also {
                it.setOnClickListener {
                    getItem(absoluteAdapterPosition)?.let { post ->
                        itemClickCallBack.onPostClick(post)
                    }
                }
            }

        fun setData(post: PostDisplayData) {
            binding.title.text = post.title
            binding.body.text = post.body
            binding.postId.text = "${post.id}"
        }
    }

}