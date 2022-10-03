package com.example.top10movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.top10movies.databinding.ActivityMovieDetailsBinding
import com.example.top10movies.repo.MovieDetailsRepo
import com.example.top10movies.ui.viewmodels.MovieDetailsViewModel
import com.example.top10movies.util.Constants.Companion.MOVIES_TO_MOVIEDETAILS

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var viewModel: MovieDetailsViewModel
    lateinit var movieId: String
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId = intent.getStringExtra(MOVIES_TO_MOVIEDETAILS).toString()

        Toast.makeText(this, movieId, Toast.LENGTH_SHORT).show()

        val movieDetails = MovieDetailsRepo()
        val viewModelProviderFactory = MovieDetailsViewModelProviderFactory(application, movieDetails, movieId)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[MovieDetailsViewModel::class.java]

    }

    private fun hideLoadingBar() {
        // hide loading bar
        // loadingBar.visibility = View.INVISIBLE
    }

    private fun showLoadingBar() {
        // loadingBar.visibility = View.VISIBLE
    }
}