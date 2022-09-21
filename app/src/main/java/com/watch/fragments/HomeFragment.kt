package com.watch.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.watch.movieslab.MovieAdapter
import com.watch.movieslab.R
import com.watch.networking.TMDbAPI
import com.watch.networking.TMDbGetPopularMoviesResponse


class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView

    // Create image list
    private val imageList = ArrayList<SlideModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val homeView =inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = homeView.findViewById(R.id.recycler_view)
        recyclerView2 = homeView.findViewById(R.id.recycler_view2)
        recyclerView3 = homeView.findViewById(R.id.recycler_view3)
        recyclerView.visibility = View.INVISIBLE
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerView2.visibility = View.INVISIBLE
        recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        recyclerView3.visibility = View.INVISIBLE
        recyclerView3.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        // Image Slider Start
        imageList.add(SlideModel(R.drawable.poster1, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.poster2, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.poster3, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.poster4, ScaleTypes.CENTER_CROP))
        val imageSlider = homeView.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)
        imageSlider.startSliding(3000)
        // Image Slider End

        TMDbAPI.requestPopularMovies(onSuccess = ::onPopularMoviesFetched,
            onError = {
                Toast.makeText(requireContext(), "shit", Toast.LENGTH_LONG).show()
            })

        TMDbAPI.requestTopRatedMovies(onSuccess = ::onTopRatedMoviesFetched,
            onError = {
                Toast.makeText(requireContext(), "top rated not here", Toast.LENGTH_LONG).show()
            })

      val any =  TMDbAPI.requestTrendingMovies(onSuccess = ::onTrendingMoviesFetched,
            onError = {
                Toast.makeText(requireContext(), "trending not here", Toast.LENGTH_LONG).show()
            })



        return homeView
    }
    private fun onPopularMoviesFetched(movieResponse: TMDbGetPopularMoviesResponse) {
        recyclerView.visibility = View.VISIBLE
        recyclerView.adapter = MovieAdapter(movieResponse.movies)
    }

    private fun onTopRatedMoviesFetched(movieResponse: TMDbGetPopularMoviesResponse) {
        recyclerView2.visibility = View.VISIBLE
        recyclerView2.adapter = MovieAdapter(movieResponse.movies)
    }

    private fun onTrendingMoviesFetched(movieResponse: TMDbGetPopularMoviesResponse) {
        recyclerView3.visibility = View.VISIBLE
        recyclerView3.adapter = MovieAdapter(movieResponse.movies)
    }



}