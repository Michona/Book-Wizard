package com.example.bookwizard.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM books ORDER BY name")
    fun getAllBooks(): LiveData<List<BookModel>>

    @Insert
    fun insertAll(list: List<BookModel>)
}