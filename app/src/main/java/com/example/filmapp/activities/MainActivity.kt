package com.example.filmapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmapp.R
import com.example.filmapp.adapters.MovieListAdapter
import com.example.filmapp.models.Movie
import com.example.filmapp.models.MovieResponse
import com.example.filmapp.services.MovieApiInterface
import com.example.filmapp.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvMoviesList: RecyclerView = findViewById(R.id.rv_movies_list)
        rvMoviesList.layoutManager = LinearLayoutManager(this)
        rvMoviesList.setHasFixedSize(true)

        getMovieData { movies: List<Movie>? ->
            if (movies != null) {
                val adapter = MovieListAdapter(movies)
                rvMoviesList.adapter = adapter
            }
        }
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Handle failure
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.movies?.let { callback(it) }
            }
        })
    }
}
