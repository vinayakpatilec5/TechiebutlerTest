package com.example.techiebutlertest.presentation.home.adapter

import com.example.techiebutlertest.domain.entities.Post
import com.example.techiebutlertest.domain.entities.PostDisplayData

interface ItemClickCallBack {
    fun onPostClick(post: PostDisplayData)
}