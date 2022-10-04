package com.example.top10movies.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.top10movies.R
import com.example.top10movies.adapter.MovieAdapter
import com.example.top10movies.databinding.FragmentMoviesBinding
import com.example.top10movies.ui.MoviesActivity
import com.example.top10movies.ui.viewmodels.MoviesViewModel
import com.example.top10movies.util.Resource

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    lateinit var viewModel: MoviesViewModel
    lateinit var movieAdapter: MovieAdapter
    lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MoviesActivity).viewModel
        setupRecyclerView()

        movieAdapter.setOnItemClickListener {
            Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                R.id.action_moviesFragment_to_movieDetailsFragment
            )
        }

        viewModel.movies.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoadingBar()
                    response.data?.let { movieResponse ->
                        movieAdapter.differ.submitList(movieResponse.movies)
                    }
                }
                is Resource.Error -> {
                    hideLoadingBar()
                    response.message?.let { message ->
                        // Log
                    }
                }
                is Resource.Loading -> {
                    showLoadingBar()
                }
            }

        })
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovieImages.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun showLoadingBar() {

    }

    private fun hideLoadingBar() {

    }

}