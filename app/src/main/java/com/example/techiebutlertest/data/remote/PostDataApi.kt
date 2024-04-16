package com.example.techiebutlertest.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PostDataApi {

    @GET("posts")
    suspend fun getPostData(
        @Query("_limit") limit: Int,
        @Query("_page") page: Int
    ): List<PostData>
}