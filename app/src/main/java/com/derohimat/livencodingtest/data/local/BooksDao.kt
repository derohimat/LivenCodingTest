package com.derohimat.livencodingtest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {
    @Query("SELECT * FROM books") fun getBooks(): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertBooks(books: List<BookEntity>): List<Long>

    @Query("DELETE FROM books") @JvmSuppressWildcards suspend fun clearBooks(): Int
}
