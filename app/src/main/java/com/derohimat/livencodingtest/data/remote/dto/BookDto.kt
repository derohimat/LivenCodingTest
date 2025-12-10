package com.derohimat.livencodingtest.data.remote.dto

import com.derohimat.livencodingtest.domain.model.Book
import com.google.gson.annotations.SerializedName

data class BookDto(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("author") val author: String,
        @SerializedName("description") val description: String,
        @SerializedName("cover_image") val coverImage: String
)

fun BookDto.toDomain(): Book {
    return Book(
            id = id.toString(),
            title = title,
            author = author,
            description = description,
            imageUrl = coverImage
    )
}
