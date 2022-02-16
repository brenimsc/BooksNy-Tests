package com.android.booksny.presentation.books

import androidx.lifecycle.*
import com.android.booksny.data.ResultBooks
import com.android.booksny.data.model.Book
import com.android.booksny.data.repository.BooksRepositoryInterface
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BooksViewModel(val repository: BooksRepositoryInterface) : ViewModel() {

    companion object {
        private const val VIEW_BOOKS = 1
        private const val VIEW_ERROR = 2
    }

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> get() = _books

    private val _viewFlipper = MutableLiveData<Pair<Int, String?>>()
    val viewFlipper: LiveData<Pair<Int, String?>> get() = _viewFlipper

    fun getBooks() {

        viewModelScope.launch {
            repository.getBooks()
            when (val result: ResultBooks = repository.getBooks()) {
                is ResultBooks.Sucess -> {
                    _books.value = result.books
                    _viewFlipper.value = Pair(VIEW_BOOKS, null)
                }
                is ResultBooks.Error -> {
                    _viewFlipper.value = Pair(VIEW_ERROR, result.erro)
                }
            }
        }
    }

    class ViewModelFactory(val booksRepository: BooksRepositoryInterface) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
                return BooksViewModel(booksRepository) as T
            }
            throw IllegalArgumentException("Unknow ViewModel class")
        }

    }

}