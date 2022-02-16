package com.android.booksny.presentation.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.booksny.data.model.Book
import com.android.booksny.databinding.ItemBooksBinding

class BooksAdapter(val books: List<Book>) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(ItemBooksBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount() = books.size

    var onItemClick : ((book: Book) -> Unit)? = null


    inner class BooksViewHolder(binding: ItemBooksBinding) : RecyclerView.ViewHolder(binding.root) {

        private val author = binding.authorBook
        private val title = binding.titleBook
        private val description = binding.descriptionBook

        fun bind(book: Book) {
            itemView.setOnClickListener {
                onItemClick?.invoke(book)
            }
            author.text = book.author
            title.text = book.title
            description.text = book.description
        }

    }
}