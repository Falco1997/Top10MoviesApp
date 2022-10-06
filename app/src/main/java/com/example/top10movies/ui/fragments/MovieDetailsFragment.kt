package com.example.top10movies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.top10movies.R
import com.example.top10movies.adapter.MovieAdapter
import com.example.top10movies.databinding.FragmentMovieDetailsBinding
import com.example.top10movies.databinding.FragmentMoviesBinding
import com.example.top10movies.ui.MoviesActivity
import com.example.top10movies.ui.viewmodels.MoviesViewModel
import com.example.top10movies.util.Resource


class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    lateinit var viewModel: MoviesViewModel
    lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MoviesActivity).viewModel

        /*
        viewModel.movies.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoadingBar()
                    hideErrorUI()
                    response.data?.let { movieResponse ->
                        movieAdapter.differ.submitList(movieResponse.movies)
                    }
                }
                is Resource.Error -> {
                    hideLoadingBar()
                    showErrorUI()
                    response.message?.let { message ->
                        hideLoadingBar()
                        binding.moviesErrorText.text = message
                    }
                }
                is Resource.Loading -> {
                    showLoadingBar()
                    hideErrorUI()
                }
            }

        })
        */


    }

}
