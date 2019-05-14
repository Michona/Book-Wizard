package com.example.bookwizard.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookwizard.data.BookModel
import com.example.bookwizard.R
import com.example.bookwizard.ui.details.DetailsFragment
import com.example.bookwizard.utils.Injector
import com.example.bookwizard.viewmodel.BooksViewModel

class BooksFragment : Fragment() {

    private lateinit var viewModel: BooksViewModel

    private val booksAdapter: BooksAdapter =
        BooksAdapter {
            onItemClick(it)
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.main_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.books_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = booksAdapter
        }


        //setup ViewModel
        val factory = Injector.provideViewModelFactory(context!!)
        viewModel = ViewModelProviders.of(activity!!, factory).get(BooksViewModel::class.java)


        viewModel.getAllBooks().observe(viewLifecycleOwner, Observer {
            booksAdapter.loadItems(it)
        })
    }

    private fun onItemClick(model: BookModel) {

        viewModel.updateSelectedBook(model)
        fragmentManager!!.beginTransaction().replace(R.id.container, DetailsFragment()).addToBackStack(null).commit()
    }
}