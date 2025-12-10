package com.derohimat.livencodingtest.di

import com.derohimat.livencodingtest.repository.BooksRepository
import com.derohimat.livencodingtest.repository.BooksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BookModule {

    @Binds
    fun bindBooksRepository(
        implementation: BooksRepositoryImpl
    ): BooksRepository
}