package com.example.techiebutlertest.presentation

import com.example.techiebutlertest.domain.entities.Post
import com.example.techiebutlertest.domain.entities.PostDisplayData

interface ItemClickCallBack {
    fun onPostClick(post: PostDisplayData)
}