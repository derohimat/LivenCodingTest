package com.derohimat.livencodingtest.domain.repository

import com.derohimat.livencodingtest.common.Resource
import com.derohimat.livencodingtest.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    fun getBooks(): Flow<Resource<List<Book>>>
}
