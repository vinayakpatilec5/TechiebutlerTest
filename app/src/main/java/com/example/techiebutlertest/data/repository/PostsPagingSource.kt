package com.example.techiebutlertest.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.techiebutlertest.domain.entities.Post
import com.example.techiebutlertest.domain.util.getResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


const val STARTING_PAGE_INDEX = 1

class PostsPagingSource(private val remote: PostDataSource.Remote) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            withContext(Dispatchers.IO) {
                remote.getPostData(params.loadSize, page).getResult(
                    {
                        LoadResult.Page(
                            data = it.data,
                            prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                            nextKey = if (it.data.isEmpty()) null else page + 1
                        )
                    }, {
                        LoadResult.Error(it.error)
                    }
                )
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}