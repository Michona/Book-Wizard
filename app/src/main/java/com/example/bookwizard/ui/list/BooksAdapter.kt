package com.example.bookwizard.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookwizard.data.BookModel
import com.example.bookwizard.R

class BooksAdapter(val onItemClick: (BookModel) -> Unit) : RecyclerView.Adapter<BookViewHolder>() {

    private var listToDisplay = listOf<BookModel>()
    private var originalList = listOf<BookModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.book_item,
                parent,
                false
            )
        )

    fun loadItems(items: List<BookModel>) {

        if (listToDisplay != items) {
            originalList = items
            listToDisplay = items
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = listToDisplay.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        holder.let {
            it.data = listToDisplay[position]

            it.view.setOnClickListener {
                onItemClick(listToDisplay[position])
            }
        }
    }
}


class BookViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private var bookTitle: TextView = view.findViewById(R.id.title)

    var data: BookModel? = null
        set(value) {

            value?.let {
                field = it
                bookTitle.text = it.name
            }
        }
}
