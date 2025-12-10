package com.derohimat.livencodingtest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.derohimat.livencodingtest.domain.model.Book

@Entity(tableName = "books")
data class BookEntity(
        @PrimaryKey val id: String,
        val title: String,
        val author: String,
        val description: String,
        val imageUrl: String
)

fun BookEntity.toDomain(): Book {
    return Book(
            id = id,
            title = title,
            author = author,
            description = description,
            imageUrl = imageUrl
    )
}

fun Book.toEntity(): BookEntity {
    return BookEntity(
            id = id,
            title = title,
            author = author,
            description = description,
            imageUrl = imageUrl
    )
}
