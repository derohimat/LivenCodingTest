package com.derohimat.livencodingtest.repository

import android.content.Context
import com.derohimat.livencodingtest.R
import com.derohimat.livencodingtest.data.Book
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

interface BooksRepository {
    suspend fun getBooks(): List<Book>
}

@Singleton
class BooksRepositoryImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : BooksRepository {

    private val books by lazy { parseBooksList() }

    override suspend fun getBooks(): List<Book> {
        delay(1500)
        return books
    }

    private fun parseBooksList(): List<Book> {
        val inputStream = context.resources.openRawResource(R.raw.books)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Json.decodeFromString(
            deserializer = ListSerializer(Product.serializer()),
            string = jsonString
        ).map {
            Book(
                id = it.id,
                name = it.name,
                imageUrl = it.thumbnails.large,
                author = it.details.authors.first(),
                description = it.description.orEmpty(),
                rating = it.details.goodreadsRating.rating.toFloat(),
                reviewCount = it.details.goodreadsRating.count.toString().take(3).toInt(),
            )
        }
    }
}