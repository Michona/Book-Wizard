package com.example.bookwizard.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookModel(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val name: String,
    val description: String
)