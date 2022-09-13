package com.watch.networking


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TMDbAPI {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val movieService = retrofit.create(MovieLabService::class.java)
    fun requestPopularMovies(
        page:Int=1,
        onSuccess: (getPopularMoviesResponse: TMDbGetPopularMoviesResponse) -> Unit,
        onError: () -> Unit
    ) {
        movieService.getPopularMovies(page = page).enqueue(object : Callback<TMDbGetPopularMoviesResponse> {
            override fun onResponse(call: Call<TMDbGetPopularMoviesResponse>, response: Response<TMDbGetPopularMoviesResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onSuccess.invoke(responseBody)
                    } else {
                        onError.invoke()
                    }
                } else {
                    onError.invoke()
                }
            }
            override fun onFailure(call: Call<TMDbGetPopularMoviesResponse>, t: Throwable) {
                onError.invoke()
                println(t.message)
            }
        })
    }
}

