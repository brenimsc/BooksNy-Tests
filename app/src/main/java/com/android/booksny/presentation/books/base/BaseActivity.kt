package com.android.booksny.presentation.books.base

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(toolbar: MaterialToolbar, title: Int, showBack: Boolean = false) {
        toolbar.title = getString(title)
        setSupportActionBar(toolbar)
        if (showBack) supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}