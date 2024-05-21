package com.example.filmapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("poster_path")
    val movieImage: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null
) : Parcelable


