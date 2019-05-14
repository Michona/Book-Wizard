package com.example.bookwizard.utils

import android.content.Context
import com.example.bookwizard.data.AppDatabase
import com.example.bookwizard.repository.BooksRepository
import com.example.bookwizard.viewmodel.ViewModelFactory

/**
 * It creates a singleton instance of ViewModelFactory so that it can be injected in fragments/activities.
 * Better approach would be some dependency injection framework like Dagger 2.
 */
object Injector {

    private fun getRepository(context: Context): BooksRepository {
        return BooksRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).bookDao())
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val repository = getRepository(context)
        return ViewModelFactory(repository)
    }
}