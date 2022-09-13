package com.watch.movieslab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ParentAdapter(parentItems: List<ParentItem>) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {
    inner class ParentViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val groupTitle: TextView
        private val childRecyclerView: RecyclerView

        init {
            childRecyclerView = v.findViewById(R.id.recycler_view)
            groupTitle = v.findViewById(R.id.movies_collection_title)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        return ParentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_main,parent,false))
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}