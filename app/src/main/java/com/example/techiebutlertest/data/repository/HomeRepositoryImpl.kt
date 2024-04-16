package com.example.techiebutlertest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.techiebutlertest.domain.entities.Post
import com.example.techiebutlertest.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val postRemoteDataSource: PostDataSource.Remote
) : HomeRepository {

    override fun getPostData(pageSize: Int): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PostsPagingSource(postRemoteDataSource) }
        ).flow
    }
}