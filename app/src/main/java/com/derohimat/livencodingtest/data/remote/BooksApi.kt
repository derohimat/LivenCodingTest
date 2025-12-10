package com.derohimat.livencodingtest.data.remote

import com.derohimat.livencodingtest.data.remote.dto.BookDto
import retrofit2.http.GET

interface BooksApi {
    @GET("books") suspend fun getBooks(): List<BookDto>
}
