package com.watch.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "4354f9ba27b39cc231cb66938d4b2b48"
interface MovieLabService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ):  Call<TMDbGetPopularMoviesResponse>

}