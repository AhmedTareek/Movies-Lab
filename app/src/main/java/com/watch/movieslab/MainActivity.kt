package com.watch.movieslab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.watch.networking.TMDbGetPopularMoviesResponse
import com.watch.networking.TMDbAPI



class MainActivity : AppCompatActivity() {

//    private lateinit var loadingBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView

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
//
        recyclerView2.visibility = View.INVISIBLE
        recyclerView2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerView3.visibility = View.INVISIBLE
        recyclerView3.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)



        imageList.add(SlideModel(R.drawable.test5,ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test3,ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test6,ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test7,ScaleTypes.CENTER_CROP))
        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)
        imageSlider.startSliding(3000)


        TMDbAPI.requestPopularMovies(onSuccess = ::onPopularMoviesFetched,
        onError = {
            Toast.makeText(this,"shit",Toast.LENGTH_LONG).show()
        })
    }

    private fun onPopularMoviesFetched(movieResponse: TMDbGetPopularMoviesResponse) {
//        loadingBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        recyclerView.adapter = MovieAdapter(movieResponse.movies)
        recyclerView2.visibility = View.VISIBLE
        recyclerView2.adapter = MovieAdapter(movieResponse.movies)
        recyclerView3.visibility = View.VISIBLE
        recyclerView3.adapter = MovieAdapter(movieResponse.movies)
    }
}