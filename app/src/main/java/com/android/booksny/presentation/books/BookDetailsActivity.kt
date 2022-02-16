package com.android.booksny.presentation.books

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.booksny.R
import com.android.booksny.databinding.ActivityBookDetailsBinding
import com.android.booksny.presentation.books.base.BaseActivity

class BookDetailsActivity : BaseActivity() {

    private lateinit var authorText: TextView
    private lateinit var descriptionText: TextView
    private lateinit var titleText: TextView
    private lateinit var binding: ActivityBookDetailsBinding
    private val title by lazy {
        intent.extras?.get(EXTRA_TITLE).toString()
    }

    private val description by lazy {
        intent.extras?.get(EXTRA_DESCRIPTION).toString()
    }

    private val author by lazy {
        intent.extras?.get(EXTRA_AUTHOR).toString()
    }

    companion object {
        private const val EXTRA_TITLE = "extraTitle"
        private const val EXTRA_DESCRIPTION = "extraDescription"
        private const val EXTRA_AUTHOR = "extraAuthor"

        fun getStartIntent(context: Context, title: String, description: String, author: String): Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESCRIPTION, description)
                putExtra(EXTRA_AUTHOR, author)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar(binding.toolbar.root, R.string.books_description, true)
        setupViews()

        titleText.text = title
        descriptionText.text = description
        authorText.text = author


    }

    private fun setupViews() {
        titleText = binding.title
        descriptionText = binding.description
        authorText = binding.author
    }


}