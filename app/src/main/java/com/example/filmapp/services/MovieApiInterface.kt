package com.example.filmapp.services

import com.example.filmapp.models.MovieDetailModel
import com.example.filmapp.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("3/discover/movie")
    fun getMovieList(
        @Query("api_key") apiKey: String = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    ): Call<MovieResponse>

    @GET("3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    ): Call<MovieDetailModel>
}
