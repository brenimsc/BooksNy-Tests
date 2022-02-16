package com.android.booksny.data.repository

import com.android.booksny.data.BooksService
import com.android.booksny.data.ResultBooks
import com.android.booksny.data.model.Book
import com.android.booksny.data.response.BooksBodyResponse
import java.lang.Exception

class BooksRepository(val service: BooksService) : BooksRepositoryInterface {


    override suspend fun getBooks() : ResultBooks {
        return try {
            val booksResponse: BooksBodyResponse = service.getBooks()
            val books: MutableList<Book> = mutableListOf()
            for (result in booksResponse.results) {
                val book = Book(
                    result.book_details[0].title,
                    result.book_details[0].author,
                    result.book_details[0].description
                )
                books.add(book)
            }
            ResultBooks.Sucess(books)
        } catch (e: Exception) {
            ResultBooks.Error(e.message.toString())
        }







//
//        return ResultBooks.Sucess(books.toList())
//            try {
//                val booksResponse: BooksBodyResponse = ApiService.service.getBooks()
//                val books: MutableList<Book> = mutableListOf()
//                for (result in booksResponse.bookResults) {
//                    val book = Book(
//                        result.bookDetails[0].title,
//                        result.bookDetails[0].author,
//                        result.bookDetails[0].description
//                    )
//                    books.add(book)
//                }
////                _books.value = books
////                _viewFlipper.value = Pair(BooksViewModel.VIEW_BOOKS, null)
//            } catch (e: Exception) {
////                _viewFlipper.value = Pair(BooksViewModel.VIEW_ERROR, e.message!!)
//            }
    }
}