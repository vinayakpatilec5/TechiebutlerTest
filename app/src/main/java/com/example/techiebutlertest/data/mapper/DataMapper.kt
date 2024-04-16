package com.example.techiebutlertest.data.mapper

import com.example.techiebutlertest.data.remote.PostData
import com.example.techiebutlertest.domain.entities.Post

fun PostData.toDomain(): Post {
    return Post(id, userId, title, body)
}