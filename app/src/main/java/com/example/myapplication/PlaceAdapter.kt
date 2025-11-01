package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Place(val imageRes: Int, val name: String)

class PlaceAdapter(private val places: List<Place>) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(places[position])
    }

    override fun getItemCount(): Int = places.size

    class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.placeImageView)
        private val textView: TextView = itemView.findViewById(R.id.placeNameTextView)

        fun bind(place: Place) {
            imageView.setImageResource(place.imageRes)
            textView.text = place.name
        }
    }
}
