package com.derohimat.livencodingtest.fakes

import com.derohimat.livencodingtest.data.local.BookEntity
import com.derohimat.livencodingtest.data.local.BooksDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeBooksDao : BooksDao {
    private val _books = MutableStateFlow<List<BookEntity>>(emptyList())

    val savedBooks: List<BookEntity>
        get() = _books.value

    override fun getBooks(): Flow<List<BookEntity>> {
        return _books
    }

    override suspend fun insertBooks(books: List<BookEntity>): List<Long> {
        _books.update { books }
        return books.map { it.id.toLongOrNull() ?: 0L } // Dummy return
    }

    override suspend fun clearBooks(): Int {
        val count = _books.value.size
        _books.update { emptyList() }
        return count
    }
}
