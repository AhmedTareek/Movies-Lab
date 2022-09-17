package com.watch.movieslab

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MovieDetailsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_layout)
        val currMovie: MovieDetails? = intent.getParcelableExtra("Movie")
        val releaseDateLabel: TextView = findViewById(R.id.release_date_label)
        val averageVoteLabel: TextView = findViewById(R.id.vote_average_label)
        val titleLabel: TextView = findViewById(R.id.title_label)
        releaseDateLabel.text = "${currMovie?.releaseDate}"
        averageVoteLabel.text = "${currMovie?.rating}"
        titleLabel.text = "${currMovie?.originalTitle}"
    }
}