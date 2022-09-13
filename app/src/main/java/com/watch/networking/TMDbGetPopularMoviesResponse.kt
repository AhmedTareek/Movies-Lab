package com.watch.networking

import com.google.gson.annotations.SerializedName
import com.watch.movieslab.MovieDetails

data class TMDbGetPopularMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MovieDetails>?,
    @SerializedName("total_pages") val pages: Int
)
