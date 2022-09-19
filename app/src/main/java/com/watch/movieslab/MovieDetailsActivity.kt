package com.watch.movieslab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val data = intent.getParcelableExtra<MovieDetails>("details")
        val title = findViewById<TextView>(R.id.title_label)
        title.text = data?.originalTitle
        val vote  = findViewById<TextView>(R.id.vote_average_label)
        vote.text = data?.rating.toString()
        val image = findViewById<ImageView>(R.id.poster_img)
        if (data?.poster != null) {
            Picasso.get().load("$BASE_IMAGE_URL${data.poster}").into(image)
        }
        val releaseDate = findViewById<TextView>(R.id.release_date_label)
        releaseDate.text = data ?.releaseDate.toString()
        val overview = findViewById<TextView>(R.id.overview_label)
        overview.text=data ?.overview


    }
}