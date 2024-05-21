package com.example.filmapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.filmapp.databinding.MovieDetaileBinding
import com.example.filmapp.models.MovieDetailModel
import com.example.filmapp.services.MovieApiInterface
import com.example.filmapp.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: MovieDetaileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieDetaileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getStringExtra("MOVIE_ID")
        if (movieId != null) {
            getMovieDetails(movieId)
        }
    }

    private fun getMovieDetails(movieId: String) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieDetails(movieId).enqueue(object : Callback<MovieDetailModel> {
            override fun onFailure(call: Call<MovieDetailModel>, t: Throwable) {
                // Handle failure
            }

            override fun onResponse(call: Call<MovieDetailModel>, response: Response<MovieDetailModel>) {
                if (response.isSuccessful) {
                    val movieDetail = response.body()
                    movieDetail?.let {
                        binding.movieTitle.text = it.title
                        binding.movieYear.text = it.releaseDate
                        binding.movieDescription.text = it.description
                        Glide.with(this@MovieDetailActivity)
                            .load("https://image.tmdb.org/t/p/w500/" + it.posterPath)
                            .into(binding.movieImage)
                    }
                }
            }
        })
    }
}
