package com.android.booksny.data.repository

import com.android.booksny.data.BooksService
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@ExperimentalCoroutinesApi
class BooksRepositoryTest {
    private val service: BooksService = mockk()

    @Test
    fun `quando chama o repository getbooks faz chamada no service`() {
        runTest {
            BooksRepository(service).getBooks()
        }

        coVerify {
            service.getBooks()
        }
    }


}