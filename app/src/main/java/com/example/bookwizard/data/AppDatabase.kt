package com.example.bookwizard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bookwizard.utils.BOOK_DATA_FILE_NAME
import com.example.bookwizard.utils.DATABASE_NAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.util.concurrent.Executors

/**
 * Builds the database based on a json file.
 * When its created it fills the database on a dedicated background thread.
 */
@Database(entities = [BookModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        fillDatabase(context.applicationContext)
                    }
                })
                .build()
        }

        /* Inserts in Room are executed on the current thread, so we insert in the background */
        private fun fillDatabase(context: Context) {
            ioThread {
                getInstance(context).bookDao().insertAll(
                    getDataFromJson(context)
                )
            }
        }

        /* Gets the data from a Json file in Assets folder. */
        private fun getDataFromJson(context: Context): List<BookModel> {
            context.assets.open(BOOK_DATA_FILE_NAME).use {
                JsonReader(it.reader()).use { reader ->

                    val bookType = object : TypeToken<List<BookModel>>() {}.type
                    return Gson().fromJson(reader, bookType)
                }
            }
        }
    }

}

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()
fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}