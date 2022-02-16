package com.android.booksny.data.repository

import com.android.booksny.data.ResultBooks

interface BooksRepositoryInterface {
    suspend fun getBooks() : ResultBooks
}