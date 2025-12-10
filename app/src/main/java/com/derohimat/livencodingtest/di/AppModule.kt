package com.derohimat.livencodingtest.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.derohimat.livencodingtest.data.local.BooksDao
import com.derohimat.livencodingtest.data.local.BooksDatabase
import com.derohimat.livencodingtest.data.remote.BooksApi
import com.derohimat.livencodingtest.data.remote.JsonBooksApi
import com.derohimat.livencodingtest.data.repository.BooksRepositoryImpl
import com.derohimat.livencodingtest.domain.repository.BooksRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideBooksApi(@ApplicationContext context: Context, gson: Gson): BooksApi {
        return JsonBooksApi(context, gson)
    }

    @Provides
    @Singleton
    fun provideBooksDatabase(app: Application): BooksDatabase {
        return Room.databaseBuilder(app, BooksDatabase::class.java, BooksDatabase.DATABASE_NAME)
                .build()
    }

    @Provides
    @Singleton
    fun provideBooksDao(db: BooksDatabase): BooksDao {
        return db.booksDao
    }

    @Provides
    @Singleton
    fun provideBooksRepository(api: BooksApi, dao: BooksDao): BooksRepository {
        return BooksRepositoryImpl(api, dao)
    }
}
