package com.derohimat.livencodingtest.data.remote

import android.content.Context
import com.derohimat.livencodingtest.R
import com.derohimat.livencodingtest.data.remote.dto.BookDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import kotlinx.coroutines.delay

class JsonBooksApi @Inject constructor(private val context: Context, private val gson: Gson) :
        BooksApi {

    override suspend fun getBooks(): List<BookDto> {
        // Simulate network delay
        delay(1500)

        return try {
            val inputStream = context.resources.openRawResource(R.raw.books)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val listType = object : TypeToken<List<BookDto>>() {}.type
            gson.fromJson(jsonString, listType)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
