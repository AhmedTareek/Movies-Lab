package com.watch.movieslab

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.navigation.NavigationBarView
import com.watch.fragments.FavoritesFragment
import com.watch.fragments.HomeFragment
import com.watch.networking.TMDbGetPopularMoviesResponse
import com.watch.networking.TMDbAPI


class MainActivity : AppCompatActivity() {


    private lateinit var navbar: NavigationBarView
    //fragments
    private val homeFragment = HomeFragment();
    private val favoritesFragment = FavoritesFragment();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSharedPreferences("com_watch_movieslab_share_prf", Context.MODE_PRIVATE).edit {
            clear()
                .commit()
        }
        setContentView(R.layout.activity_main)
        // set first screen
        replaceFragment(homeFragment)
        //Navbar start
        navbar = findViewById(R.id.nav_bar);
        navbar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_nav -> {
                    replaceFragment(homeFragment)
                }
                R.id.favorites_nav -> {
                    replaceFragment(favoritesFragment)
                }
            }
            true
        }


    }


    //Fragment Related Function Start
    private fun replaceFragment(fragment: Fragment) {
        val fragments = supportFragmentManager.beginTransaction()
        fragments.replace(R.id.fragment_container, fragment)
        fragments.commit()
    }
    //Fragment Related Function End
}
