package com.watch.movieslab

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import com.google.android.material.switchmaterial.SwitchMaterial
import com.squareup.picasso.Picasso
import com.watch.fragments.FavoritesFragment

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var mySharedPreferences: SharedPreferences
    private lateinit var favoritesSwitch: SwitchMaterial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        mySharedPreferences =getSharedPreferences("com_watch_movieslab_share_prf",Context.MODE_PRIVATE)



        val data = intent.getParcelableExtra<MovieDetails>("details")

        val checkedFlag = mySharedPreferences.getBoolean(data?.originalTitle,false)
        favoritesSwitch=findViewById(R.id.favorite_switch)

        if (checkedFlag){
            favoritesSwitch.isChecked=true
        }

        favoritesSwitch.setOnCheckedChangeListener { _, isChecked ->

                val favDB=favouriteBase

                    favDB.setFavouriteListOfMovies(data!!)
                  mySharedPreferences.edit {
                      putBoolean(data?.originalTitle,isChecked)
                          .commit()
                      Toast.makeText(
                          this@MovieDetailsActivity,
                          "i made this",
                          Toast.LENGTH_LONG
                      ).show()

        }
       }

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