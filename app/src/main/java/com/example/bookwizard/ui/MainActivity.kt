package com.example.bookwizard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookwizard.R
import com.example.bookwizard.ui.list.BooksFragment
import com.example.bookwizard.utils.APP_NAME

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar!!.title = APP_NAME
        }

        supportFragmentManager.beginTransaction().add(
            R.id.container,
            BooksFragment()
        ).commit()
    }
}
