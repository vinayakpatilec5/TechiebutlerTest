package com.example.techiebutlertest.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.techiebutlertest.domain.entities.PostDisplayData

class PostComparator : DiffUtil.ItemCallback<PostDisplayData>() {
    override fun areItemsTheSame(oldItem: PostDisplayData, newItem: PostDisplayData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PostDisplayData, newItem: PostDisplayData): Boolean =
        oldItem.id == newItem.id
}