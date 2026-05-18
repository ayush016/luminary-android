package com.example.luminary.navigation

import kotlinx.serialization.Serializable

@Serializable data object HomeRoute
@Serializable data object DiscoverRoute
@Serializable data object BookmarksRoute
@Serializable data class ArticleDetailRoute(val articleId: Long)
