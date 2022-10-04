package com.example.top10movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.top10movies.R
import com.example.top10movies.repo.MoviesRepo
import com.example.top10movies.ui.viewmodels.MoviesViewModel

class MoviesActivity : AppCompatActivity() {
    lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val moviesRepo = MoviesRepo()
        val viewModelProviderFactory = MovieViewModelProviderFactory(application, moviesRepo)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[MoviesViewModel::class.java]
    }
}