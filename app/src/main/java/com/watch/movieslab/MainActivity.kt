package com.watch.movieslab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.watch.networking.TMDbGetPopularMoviesResponse
import com.watch.networking.TMDbAPI


class MainActivity : AppCompatActivity() {

    private lateinit var loadingBar: ProgressBar
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        loadingBar = findViewById(R.id.loading_bar)
        recyclerView.visibility = View.INVISIBLE
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


       TMDbAPI.requestPopularMovies(onSuccess = ::onPopularMoviesFetched,
        onError = {
            Toast.makeText(this,"shit",Toast.LENGTH_LONG).show()
        })
    }

    private fun onPopularMoviesFetched(movieResponse: TMDbGetPopularMoviesResponse) {
        loadingBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        recyclerView.adapter = MovieAdapter(movieResponse.movies)
    }
}