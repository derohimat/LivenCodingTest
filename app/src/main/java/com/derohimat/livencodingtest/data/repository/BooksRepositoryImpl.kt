package com.derohimat.livencodingtest.data.repository

import com.derohimat.livencodingtest.common.Resource
import com.derohimat.livencodingtest.data.local.BooksDao
import com.derohimat.livencodingtest.data.local.toDomain
import com.derohimat.livencodingtest.data.local.toEntity
import com.derohimat.livencodingtest.data.remote.BooksApi
import com.derohimat.livencodingtest.data.remote.dto.toDomain
import com.derohimat.livencodingtest.domain.model.Book
import com.derohimat.livencodingtest.domain.repository.BooksRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class BooksRepositoryImpl
@Inject
constructor(private val api: BooksApi, private val dao: BooksDao) : BooksRepository {

    override fun getBooks(): Flow<Resource<List<Book>>> = flow {
        emit(Resource.Loading())

        try {
            val remoteBooks = api.getBooks()
            dao.clearBooks()
            dao.insertBooks(remoteBooks.map { it.toDomain().toEntity() })
        } catch (e: Exception) {
            // Fallback to cache
            try {
                // Get the first item from the Flow, which is the current list in DB
                val cachedBooksEntity = dao.getBooks().first()
                val cachedBooks = cachedBooksEntity.map { it.toDomain() }

                if (cachedBooks.isNotEmpty()) {
                    emit(
                            Resource.Error(
                                    message = e.localizedMessage ?: "An unexpected error occurred",
                                    data = cachedBooks
                            )
                    )
                } else {
                    emit(
                            Resource.Error(
                                    message = e.localizedMessage ?: "An unexpected error occurred"
                            )
                    )
                }
            } catch (flowError: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
            }
            return@flow
        }

        val newCachedBooks = dao.getBooks().first().map { it.toDomain() }
        emit(Resource.Success(newCachedBooks))
    }
}
