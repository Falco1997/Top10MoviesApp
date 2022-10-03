package com.example.top10movies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.top10movies.adapter.MovieAdapter
import com.example.top10movies.databinding.ActivityMoviesBinding
import com.example.top10movies.repo.MoviesRepo
import com.example.top10movies.ui.viewmodels.MoviesViewModel
import com.example.top10movies.util.Constants.Companion.MOVIES_TO_MOVIEDETAILS
import com.example.top10movies.util.Resource

class MoviesActivity : AppCompatActivity() {
    lateinit var viewModel: MoviesViewModel
    lateinit var movieAdapter: MovieAdapter

    private lateinit var binding: ActivityMoviesBinding

    val TAG = "Break"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        val moviesRepo = MoviesRepo()
        val viewModelProviderFactory = MovieViewModelProviderFactory(application, moviesRepo)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[MoviesViewModel::class.java]

        movieAdapter.setOnItemClickListener { movie ->
            Toast.makeText(this, "you clicked " + movie.title + "with id: " + movie.id, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MovieDetailsActivity::class.java).apply {
                putExtra(MOVIES_TO_MOVIEDETAILS, movie.id.toString())
            }
            startActivity(intent)
        }

        viewModel.popularMovies.observe(this, Observer { response ->
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