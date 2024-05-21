
package com.example.filmapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("overview")
    val description: String,

    @SerializedName("poster_path")
    val posterPath: String
) : Parcelable
