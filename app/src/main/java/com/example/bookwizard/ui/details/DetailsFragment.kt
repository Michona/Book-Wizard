package com.example.bookwizard.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bookwizard.R
import com.example.bookwizard.utils.Injector
import com.example.bookwizard.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.details_fragment.*

class DetailsFragment : Fragment(){

    private lateinit var viewModel: BooksViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.details_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup ViewModel
        val factory = Injector.provideViewModelFactory(context!!)
        viewModel = ViewModelProviders.of(activity!!, factory).get(BooksViewModel::class.java)

        viewModel.getSelectedBook().observe(viewLifecycleOwner, Observer {
            updateTitle(it.name)
            updateDescription(it.description)
        })
    }

    private fun updateTitle(title: String) {
        details_title.text = title
    }
    private fun updateDescription(descriptionText: String) {
        details_description_view.text = descriptionText
    }
}