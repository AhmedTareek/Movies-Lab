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

class MovieAdapter( val moviesDetails: List<MovieDetails>?) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        //        var releaseDate: TextView
//        var rating: TextView
//        var title: TextView
        var poster: ImageView

        init {
//            releaseDate = v.findViewById(R.id.release_date_label)
//            rating = v.findViewById(R.id.vote_average_label)
//            title = v.findViewById(R.id.title_label)
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
//        holder.releaseDate.text = currPost?.releaseDate?.substring(0, 4) //get only the year as string is in YYYY-MM-DD format
//        holder.rating.text = currPost?.rating.toString()
//        holder.title.text = currPost?.originalTitle
        if (currPost?.poster != null) {
            Picasso.get().load("$BASE_IMAGE_URL${currPost.poster}").into(holder.poster)
        }


    fun getItemCount(): Int {
        return moviesDetails?.size ?: 0
    }
}


//*********************************************************************************************/

inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val movieTitle: TextView
    private val movieRating: TextView
    private val movieDate : TextView
    private val moviePoster : TextView



    init {
        movieTitle = itemView.findViewById(R.id.title_label)
        movieRating = itemView.findViewById(R.id.vote_average_label)
        movieDate = itemView.findViewById(R.id.release_date_label)
        moviePoster =  itemView.findViewById(R.id.poster_img)
        moviePoster.setOnClickListener() {
            openDetailsActivity(movieTitle.toString())
        }
    }

    private fun openDetailsActivity(movieTitle : String) {
        val currPost = moviesDetails?.get(position)
        val intent = Intent(itemView.context, MovieDetailsActivity::class.java)
        val movieObj = MovieDetails(currPost?.releaseDate,currPost?.originalTitle,
            (currPost?.rating ?: 0) as Float,currPost?.poster)

        intent.putExtra("Movie", movieObj)
        itemView.context.startActivity(intent)
    }

    fun bindFruit(movies: MovieDetails) {
        movieTitle.text = movies.originalTitle
        movieRating.text = movies.rating.toString()
        movieDate.text = movies.releaseDate
    }

}

    override fun getItemCount(): Int {
        return moviesDetails?.size ?: 0
    }


}

