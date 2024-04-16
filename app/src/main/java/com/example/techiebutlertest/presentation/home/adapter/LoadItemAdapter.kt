package com.example.techiebutlertest.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.techiebutlertest.R
import com.example.techiebutlertest.databinding.ItemPostLoadBinding

class LoadItemAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadItemAdapter.LoadStateViewHolder>() {
    inner class LoadStateViewHolder(private val binding: ItemPostLoadBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val retry: Button = binding.retryBtn
            .also {
                it.setOnClickListener {
                    retry()
                }
            }

        fun bind(loadState: LoadState) {
            binding.itemLoader.isVisible = loadState is LoadState.Loading
            binding.errorLayout.isVisible = loadState is LoadState.Error
        }
    }


    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        val binding: ItemPostLoadBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_post_load, parent, false
        )
        return LoadStateViewHolder(binding)
    }
}