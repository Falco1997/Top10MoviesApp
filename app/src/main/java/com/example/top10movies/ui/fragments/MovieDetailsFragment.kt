package com.example.top10movies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.top10movies.R
import com.example.top10movies.api.data.MovieDetails
import com.example.top10movies.databinding.FragmentMovieDetailsBinding
import com.example.top10movies.ui.MoviesActivity
import com.example.top10movies.ui.viewmodels.MoviesViewModel
import com.example.top10movies.util.Constants.Companion.API_KEY
import com.example.top10movies.util.Constants.Companion.GLIDE_BASE_URL_SMALL
import com.example.top10movies.util.Constants.Companion.MOVIE_ID_KEY
import com.example.top10movies.util.Resource


class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    lateinit var viewModel: MoviesViewModel
    lateinit var binding: FragmentMovieDetailsBinding
    lateinit var movieId: String

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

        setFragmentResultListener(MOVIE_ID_KEY) { key, bundle ->
            movieId = bundle.getString("movieId").toString()
            viewModel.getMovieDetailsById(API_KEY, movieId)
        }

        binding.movieDetailsErrorButton.setOnClickListener {
            viewModel.getMovieDetailsById(API_KEY, movieId)
        }

        viewModel.movieDetails.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoadingBar()
                    hideErrorUI()
                    response.data?.let { movieResponse ->
                        showMovieDetails(movieResponse)
                    }
                }
                is Resource.Error -> {
                    hideLoadingBar()
                    showErrorUI()
                    response.message?.let { message ->
                        hideLoadingBar()
                        binding.movieDetailsErrorText.text = message
                    }
                }
                is Resource.Loading -> {
                    showLoadingBar()
                    hideErrorUI()
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    private fun showMovieDetails(movieDetails: MovieDetails) {
        binding.movieDetailsName.text = movieDetails.title
        binding.movieDetailsReleaseDate.text = movieDetails.release_date
        binding.movieDetailsPopularityScore.text = movieDetails.popularity.toString()
        binding.movieDetailsDescription.text = movieDetails.homepage

        Glide.with(this)
            .load(GLIDE_BASE_URL_SMALL + movieDetails.poster_path)
            .transform(RoundedCorners(10))
            //.override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .into(binding.movieDetailsImageView)
    }

    private fun showLoadingBar() {
        binding.movieDetailsProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoadingBar() {
        binding.movieDetailsProgressBar.visibility = View.INVISIBLE
    }

    private fun showErrorUI() {
        binding.movieDetailsErrorText.visibility = View.VISIBLE
        binding.movieDetailsErrorButton.visibility = View.VISIBLE
    }

    private fun hideErrorUI() {
        binding.movieDetailsErrorText.visibility = View.INVISIBLE
        binding.movieDetailsErrorButton.visibility = View.INVISIBLE
    }

}
