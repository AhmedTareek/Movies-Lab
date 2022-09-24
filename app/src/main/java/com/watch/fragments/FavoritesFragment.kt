package com.watch.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.watch.movieslab.*


class FavoritesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val favView = inflater.inflate(R.layout.fragment_favorites, container, false)

        val favDB = FavouriteBase

        recyclerView = favView.findViewById(R.id.favorite_list_recyclerview)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        var favListOfMovies = favDB.getFavouriteListOfMovies()
        if (favListOfMovies.isNotEmpty()) {
            showFavouritePage(favListOfMovies)
        } else {
            Toast.makeText(
                requireContext(),
                "There is no favourite Movie until Now",
                Toast.LENGTH_LONG
            ).show()
        }
        return favView
    }

    private fun showFavouritePage(list: MutableList<MovieDetails>) {
        recyclerView.visibility = View.VISIBLE
        recyclerView.adapter = MovieAdapter(list)
    }

}