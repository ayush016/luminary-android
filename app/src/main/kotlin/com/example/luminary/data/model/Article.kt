package com.example.luminary.data.model

data class Article(
    val id: Long,
    val title: String,
    val category: String,
    val author: String,
    val authorAvatarUrl: String,
    val readTime: String,
    val summary: String,
    val body: String,
    val imageUrl: String,
    val publishedAt: String,
    val isFeatured: Boolean = false,
    val isBookmarked: Boolean = false
)
