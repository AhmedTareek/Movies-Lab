package com.watch.movieslab

object FavouriteBase {
    private val favouriteListOfMovies =mutableListOf<MovieDetails>()
    fun setFavouriteListOfMovies(movie:MovieDetails){

            favouriteListOfMovies.add(movie)

    }

    fun getFavouriteListOfMovies (): MutableList<MovieDetails> {
        return favouriteListOfMovies
    }

    fun removeFromListOfMovies(movie:MovieDetails){
        favouriteListOfMovies.remove(movie)
    }
}