package com.example.techiebutlertest.data.repository

import com.example.techiebutlertest.data.mapper.toDomain
import com.example.techiebutlertest.data.remote.PostDataApi
import com.example.techiebutlertest.domain.entities.Post
import com.example.techiebutlertest.domain.util.AppResult

class PostDataRemoteSource(private val api: PostDataApi) : PostDataSource.Remote {

    override suspend fun getPostData(limit: Int, page: Int): AppResult<List<Post>> = try {
        AppResult.Success(
            data = api.getPostData(
                limit, page
            ).map {
                it.toDomain()
            }
        )
    } catch (e: Exception) {
        AppResult.Error(e)
    }
}