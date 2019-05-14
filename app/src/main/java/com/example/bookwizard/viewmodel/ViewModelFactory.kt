package com.example.bookwizard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookwizard.repository.BooksRepository

class ViewModelFactory(private val repository: BooksRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = BooksViewModel(repository) as T
}