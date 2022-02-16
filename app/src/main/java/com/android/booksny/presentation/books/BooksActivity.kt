package com.android.booksny.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.booksny.R
import com.android.booksny.data.model.Book
import com.android.booksny.data.repository.BooksRepository
import com.android.booksny.databinding.ActivityBooksBinding
import com.android.booksny.presentation.books.base.BaseActivity
import com.google.android.material.appbar.MaterialToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksActivity : BaseActivity() {

    private lateinit var txtError: TextView
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var bookAdapter: BooksAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: MaterialToolbar
    private lateinit var binding: ActivityBooksBinding
    private val viewModel: BooksViewModel by viewModel()
    private val listBooks by lazy {
        mutableListOf<Book>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        bookAdapter = BooksAdapter(listBooks)
        setupToolbar(toolbar, R.string.app_name)



        viewModel.books.observe(this) {
            with(recyclerView) {
                layoutManager = LinearLayoutManager(this@BooksActivity)
                setHasFixedSize(true)
                listBooks.addAll(it)
                adapter = bookAdapter
                bookAdapter.onItemClick = {
                    startActivity(
                        BookDetailsActivity.getStartIntent(
                            this@BooksActivity,
                            it.title,
                            it.description,
                            it.author
                        )
                    )
                }

            }
        }

        viewModel.viewFlipper.observe(this) { viewFlipperBooks ->
            viewFlipperBooks?.let {
                viewFlipper.displayedChild = it.first
                it.second?.let {
                    txtError.text = "${getString(R.string.books_error)} \n $it"
                }
            }

        }

        viewModel.getBooks()


    }

    private fun setupViews() {
        toolbar = binding.toolbarMain.root
        recyclerView = binding.recyclerView
        viewFlipper = binding.viewflipper
        txtError = binding.txtError
    }
}