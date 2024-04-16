package com.example.techiebutlertest.domain.entities

import android.text.Spannable

data class PostDisplayData(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: Spannable
)