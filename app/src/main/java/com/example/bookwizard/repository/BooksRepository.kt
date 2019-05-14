package com.example.bookwizard.repository

import androidx.lifecycle.LiveData
import com.example.bookwizard.data.BookDao
import com.example.bookwizard.data.BookModel

/**
 * This handles data operations.
 * The repository should hold all the remote data sources as well as provide access to the local database.
 */
class BooksRepository private constructor(private val bookDao: BookDao) {

    fun getAll(): LiveData<List<BookModel>> = bookDao.getAllBooks()

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: BooksRepository? = null

        fun getInstance(bookDao: BookDao) =
            instance ?: synchronized(this) {
                instance ?: BooksRepository(bookDao).also { instance = it }
            }
    }
}