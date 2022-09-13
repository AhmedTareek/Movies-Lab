package com.watch.movieslab

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

        var releaseDate: TextView
        var rating: TextView
        var title: TextView
        var poster: ImageView

        init {
            releaseDate = v.findViewById(R.id.release_date_label)
            rating = v.findViewById(R.id.vote_average_label)
            title = v.findViewById(R.id.title_label)
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
        holder.releaseDate.text = currPost?.releaseDate?.substring(0, 4) //get only the year as string is in YYYY-MM-DD format
        holder.rating.text = currPost?.rating.toString()
        holder.title.text = currPost?.originalTitle
        if (currPost?.poster != null) {
            Picasso.get().load("$BASE_IMAGE_URL${currPost.poster}").into(holder.poster)
        }

    }

    override fun getItemCount(): Int {
        return moviesDetails?.size ?: 0
    }
}

