package com.example.top10movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.top10movies.R
import com.example.top10movies.adapter.MovieAdapter
import com.example.top10movies.api.data.Movie
import com.example.top10movies.databinding.ActivityMoviesBinding
import com.example.top10movies.repo.MovieRepo
import com.example.top10movies.util.Constants.Companion.API_KEY
import com.example.top10movies.util.Resource

class MoviesActivity : AppCompatActivity() {
    lateinit var viewModel: MovieViewModel
    lateinit var movieAdapter: MovieAdapter

    private lateinit var binding: ActivityMoviesBinding

    val TAG = "Break"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieRepo = MovieRepo()
        val viewModelProviderFactory = MovieViewModelProviderFactory(movieRepo)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[MovieViewModel::class.java]

        viewModel.trendingMovies.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideLoadingBar()
                    response.data?.let { movieResponse ->
                        setupRecyclerView()
                    }
                }
                is Resource.Error -> {
                    hideLoadingBar()
                    response.message?.let { message ->
                        Log.e(TAG, "Error: $message")
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
            layoutManager = LinearLayoutManager(this@MoviesActivity)
        }
    }

    private fun hideLoadingBar() {
        // hide loading bar
        // loadingBar.visibility = View.INVISIBLE
    }

    private fun showLoadingBar() {
        // loadingBar.visibility = View.VISIBLE
    }
}