package com.watch.movieslab

object favouriteBase {
    private val favouriteListOfMovies =mutableListOf<MovieDetails>()
    fun setFavouriteListOfMovies(movie:MovieDetails){

            favouriteListOfMovies.add(movie)

    }

    fun getFavouriteListOfMovies (): MutableList<MovieDetails> {
        return favouriteListOfMovies
    }
}