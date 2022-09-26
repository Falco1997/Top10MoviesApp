package com.example.top10movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.top10movies.R
import com.example.top10movies.repo.MovieRepo

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieRepo = MovieRepo()
        val viewModelProviderFactory = MovieViewModelProviderFactory(movieRepo)
        movieViewModel = ViewModelProvider(this, viewModelProviderFactory).get(MovieViewModel::class.java)

        
    }
}