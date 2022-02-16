package com.android.booksny.data

import com.android.booksny.data.model.Book
import com.android.booksny.presentation.books.BooksActivity

sealed class ResultBooks {
    class Sucess(val books: List<Book>) : ResultBooks()
    class Error(val erro: String) : ResultBooks()
}