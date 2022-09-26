package com.example.top10movies.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.top10movies.R
import com.example.top10movies.api.data.Movie
import com.example.top10movies.databinding.ActivityMoviesBinding
import com.example.top10movies.ui.MoviesActivity
import com.example.top10movies.util.Constants
import com.example.top10movies.util.Constants.Companion.GLIDE_BASE_URL
import com.example.top10movies.util.Constants.Companion.IMAGE_URL_BEGINNING
import com.example.top10movies.util.Constants.Companion.IMAGE_URL_END
import com.squareup.picasso.Picasso

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movieimage,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
            holder.itemView.apply {

            Glide.with(this)
                .load(GLIDE_BASE_URL + movie.poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.itemView.findViewById(R.id.movieImageView))

                setOnClickListener {
                    onItemClickListener?.let {
                        it(movie)
                    }
                }
            }
        }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }
}