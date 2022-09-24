package com.watch.movieslab

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"

class MovieAdapter(private val moviesDetails: List<MovieDetails>?) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var poster: ImageView

        init {

            v.setOnClickListener {
                val currMovie = moviesDetails?.get(layoutPosition)
                val intent = Intent(v.context,MovieDetailsActivity::class.java).apply {
                    putExtra("details",currMovie)
                }
                v.context.startActivity(intent)


            }

            poster = v.findViewById(R.id.poster_img)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.popular_movies, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currPost = moviesDetails?.get(position)
        if (currPost?.poster != null) {
            Picasso.get().load("$BASE_IMAGE_URL${currPost.poster}").into(holder.poster)
        }

}
    override fun getItemCount(): Int {
        return moviesDetails?.size ?: 0
    }


}

