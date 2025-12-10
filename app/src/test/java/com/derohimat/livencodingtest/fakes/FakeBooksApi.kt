package com.derohimat.livencodingtest.fakes

import com.derohimat.livencodingtest.data.remote.BooksApi
import com.derohimat.livencodingtest.data.remote.dto.BookDto
import java.io.IOException

class FakeBooksApi : BooksApi {
    private val booksResponse = mutableListOf<BookDto>()
    private var shouldReturnError = false

    fun setBooks(books: List<BookDto>) {
        booksResponse.clear()
        booksResponse.addAll(books)
    }

    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getBooks(): List<BookDto> {
        if (shouldReturnError) {
            throw IOException("Fake Network Error")
        }
        return booksResponse
    }
}
