package com.derohimat.livencodingtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {
    abstract val booksDao: BooksDao

    companion object {
        const val DATABASE_NAME = "books_db"
    }
}
