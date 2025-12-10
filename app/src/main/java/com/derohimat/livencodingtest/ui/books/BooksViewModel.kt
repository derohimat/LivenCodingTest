package com.derohimat.livencodingtest.ui.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.derohimat.livencodingtest.common.Resource
import com.derohimat.livencodingtest.domain.model.Book
import com.derohimat.livencodingtest.domain.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class BooksViewModel @Inject constructor(private val repository: BooksRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val uiState: StateFlow<BooksUiState> = _uiState.asStateFlow()

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            repository.getBooks().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = BooksUiState.Loading
                    }
                    is Resource.Success -> {
                        val books = result.data
                        if (books.isNullOrEmpty()) {
                            _uiState.value = BooksUiState.Empty
                        } else {
                            _uiState.value = BooksUiState.Success(books)
                        }
                    }
                    is Resource.Error -> {
                        val books = result.data
                        if (!books.isNullOrEmpty()) {
                            // Start with showing data, maybe could show a toast for error
                            _uiState.value = BooksUiState.Success(books)
                            // Ideally use a separate channel for events (error messages)
                        } else {
                            _uiState.value = BooksUiState.Error(result.message ?: "Unknown Error")
                        }
                    }
                }
            }
        }
    }
}

sealed class BooksUiState {
    object Loading : BooksUiState()
    object Empty : BooksUiState()
    data class Success(val books: List<Book>) : BooksUiState()
    data class Error(val message: String) : BooksUiState()
}
