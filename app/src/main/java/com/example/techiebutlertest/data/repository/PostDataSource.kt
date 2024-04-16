package com.example.techiebutlertest.data.repository

import com.example.techiebutlertest.domain.entities.Post
import com.example.techiebutlertest.domain.util.AppResult

interface PostDataSource {
    interface Remote {
        suspend fun getPostData(
            limit: Int,
            page: Int
        ): AppResult<List<Post>>
    }
}