package com.derohimat.livencodingtest.data.repository

import com.derohimat.livencodingtest.common.Resource
import com.derohimat.livencodingtest.data.remote.dto.BookDto
import com.derohimat.livencodingtest.fakes.FakeBooksApi
import com.derohimat.livencodingtest.fakes.FakeBooksDao
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BooksRepositoryTest {

    private lateinit var repository: BooksRepositoryImpl
    private lateinit var api: FakeBooksApi
    private lateinit var dao: FakeBooksDao

    @Before
    fun setup() {
        api = FakeBooksApi()
        dao = FakeBooksDao()
        repository = BooksRepositoryImpl(api, dao)
    }

    @Test
    fun `getBooks fetches from api and saves to db and returns success`() = runTest {
        val fakeBooks =
                listOf(
                        BookDto(1, "Title 1", "Author 1", "Desc 1", "img1"),
                        BookDto(2, "Title 2", "Author 2", "Desc 2", "img2")
                )
        api.setBooks(fakeBooks)

        val flow = repository.getBooks()
        val results = flow.toList()

        // Expect Loading then Success
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Success)

        val successData = (results[1] as Resource.Success).data
        assertEquals(2, successData?.size)
        assertEquals("Title 1", successData?.get(0)?.title)

        // Verify saved to DAO
        assertEquals(2, dao.savedBooks.size)
    }

    @Test
    fun `getBooks emits error when api fails and db empty`() = runTest {
        api.setShouldReturnError(true)

        val flow = repository.getBooks()
        val results = flow.toList()

        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Error)
        assertTrue((results[1] as Resource.Error).data.isNullOrEmpty())
    }
}
