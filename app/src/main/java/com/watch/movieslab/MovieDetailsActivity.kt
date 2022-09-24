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

                val favDB=FavouriteBase

                    if (isChecked) {
                        favDB.setFavouriteListOfMovies(data!!)
                        Toast.makeText(
                            this@MovieDetailsActivity,
                            "added to favourite",
                            Toast.LENGTH_LONG
                        ).show()
                    }else {
                        favDB.removeFromListOfMovies(data!!)
                        Toast.makeText(
                            this@MovieDetailsActivity,
                            "removed from favourite",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                  mySharedPreferences.edit {
                      putBoolean(data?.originalTitle,isChecked)
                          .commit()
