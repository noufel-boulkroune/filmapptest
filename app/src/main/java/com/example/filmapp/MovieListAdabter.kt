package com.example.filmapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmapp.activities.MovieDetailActivity
import com.example.filmapp.databinding.ItemMovieBinding
import com.example.filmapp.models.Movie

class MovieListAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        fun bindMovie(movie: Movie) {
            binding.movieTitle.text = movie.title
            binding.movieReleaseDate.text = movie.releaseDate
            Glide.with(binding.root).load(IMAGE_BASE + movie.movieImage).into(binding.movieImg)

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, MovieDetailActivity::class.java).apply {
                    putExtra("MOVIE_ID", movie.id)
                }
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}
