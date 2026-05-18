package com.example.luminary.feature.home

import com.example.luminary.data.model.Article

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Ready(
        val featured: Article,
        val articles: List<Article>
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
