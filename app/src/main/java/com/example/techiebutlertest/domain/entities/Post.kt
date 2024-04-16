package com.example.techiebutlertest.domain.entities

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)