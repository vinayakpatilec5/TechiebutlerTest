package com.example.techiebutlertest.domain.repository

import androidx.paging.PagingData
import com.example.techiebutlertest.domain.entities.Post
import com.example.techiebutlertest.domain.util.AppResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPostData(pageSize: Int): Flow<PagingData<Post>>
}