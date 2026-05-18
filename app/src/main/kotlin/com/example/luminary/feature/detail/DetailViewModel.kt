package com.example.luminary.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.luminary.data.model.Article
import com.example.luminary.data.repository.ArticleRepository
import com.example.luminary.navigation.ArticleDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface DetailUiState {
    data object Loading : DetailUiState
    data class Ready(val article: Article) : DetailUiState
}

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ArticleRepository
) : ViewModel() {

    private val route: ArticleDetailRoute = savedStateHandle.toRoute()

    val uiState: StateFlow<DetailUiState> = repository
        .observeArticle(route.articleId)
        .map { article ->
            if (article != null) DetailUiState.Ready(article)
            else DetailUiState.Loading
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), DetailUiState.Loading)
}
