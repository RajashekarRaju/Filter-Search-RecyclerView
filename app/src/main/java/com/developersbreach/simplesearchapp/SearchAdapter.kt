package com.developersbreach.simplesearchapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchAdapter(
    private val sportsList: List<Sports>,
    private val listener: SportsAdapterListener
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.search_title_text_view)
        val iconImageView: ImageView = itemView.findViewById(R.id.search_icon_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_search, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val sports: Sports = sportsList[position]
        holder.titleTextView.text = sports.title
        holder.iconImageView.setImageResource(sports.icon)

        holder.itemView.setOnClickListener {
            listener.onSportSelected(sports)
        }
    }

    override fun getItemCount() = sportsList.size

    interface SportsAdapterListener {
        fun onSportSelected(sports: Sports?)
    }
}