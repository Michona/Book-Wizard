package com.example.bookwizard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookwizard.data.BookModel
import com.example.bookwizard.repository.BooksRepository

class BooksViewModel internal constructor(private val repository: BooksRepository) : ViewModel() {

    private val selectedBook: MutableLiveData<BookModel> = MutableLiveData()

    fun updateSelectedBook(model: BookModel) {
        selectedBook.value = model
    }

    /* Get all books from the repository, which in turn gets them from the database. */
    fun getAllBooks(): LiveData<List<BookModel>> = repository.getAll()

    /* Returns the current selected book that the DetailsFragment needs to show the proper data. */
    fun getSelectedBook() = selectedBook as LiveData<BookModel>
}