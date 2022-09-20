package com.watch.movieslab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
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

    //    private lateinit var loadingBar: ProgressBar
    private lateinit var navbar: NavigationBarView
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView

    //fragments
    private val homeFragment = HomeFragment();
    private val favoritesFragment = FavoritesFragment();

    private val imageList = ArrayList<SlideModel>() // Create image list


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        recyclerView = findViewById(R.id.recycler_view)
        recyclerView2 = findViewById(R.id.recycler_view2)
        recyclerView3 = findViewById(R.id.recycler_view3)
//        loadingBar = findViewById(R.id.loading_bar)
        recyclerView.visibility = View.INVISIBLE
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerView2.visibility = View.INVISIBLE
        recyclerView2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerView3.visibility = View.INVISIBLE
        recyclerView3.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        // Image Slider Start
        imageList.add(SlideModel(R.drawable.poster1, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.poster2, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.poster3, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.poster4, ScaleTypes.CENTER_CROP))
        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)
        imageSlider.startSliding(3000)
        // Image Slider End

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
        //Navbar end

        TMDbAPI.requestPopularMovies(onSuccess = ::onPopularMoviesFetched,
            onError = {
                Toast.makeText(this, "shit", Toast.LENGTH_LONG).show()
            })

        TMDbAPI.requestTopRatedMovies(onSuccess = ::onTopRatedMoviesFetched,
            onError = {
                Toast.makeText(this, "top rated not here", Toast.LENGTH_LONG).show()
            })
    }

    private fun onPopularMoviesFetched(movieResponse: TMDbGetPopularMoviesResponse) {
//        loadingBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        recyclerView.adapter = MovieAdapter(movieResponse.movies)
       // recyclerView2.visibility = View.VISIBLE
       // recyclerView2.adapter = MovieAdapter(movieResponse.movies)
        recyclerView3.visibility = View.VISIBLE
        recyclerView3.adapter = MovieAdapter(movieResponse.movies)
    }

    private fun onTopRatedMoviesFetched(movieResponse: TMDbGetPopularMoviesResponse) {
//        loadingBar.visibility = View.GONE
     //   recyclerView.visibility = View.VISIBLE
       // recyclerView.adapter = MovieAdapter(movieResponse.movies)
        recyclerView2.visibility = View.VISIBLE
        recyclerView2.adapter = MovieAdapter(movieResponse.movies)
        //recyclerView3.visibility = View.VISIBLE
       //recyclerView3.adapter = MovieAdapter(movieResponse.movies)
    }

    //Fragment Related Function Start
    private fun replaceFragment(fragment: Fragment) {

        val fragments = supportFragmentManager.beginTransaction();
        fragments.replace(R.id.fragment_container, fragment)
        fragments.commit()
    }
    //Fragment Related Function End
}
