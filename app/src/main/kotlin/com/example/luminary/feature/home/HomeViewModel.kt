package com.example.luminary.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luminary.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init { loadArticles() }

    fun loadArticles() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            repository.observeArticles()
                .catch { _uiState.value = HomeUiState.Error(it.message ?: "Failed to load") }
                .collect { articles ->
                    _uiState.value = HomeUiState.Ready(
                        featured = articles.first { it.isFeatured },
                        articles = articles.filter { !it.isFeatured }
                    )
                }
        }
    }
}
