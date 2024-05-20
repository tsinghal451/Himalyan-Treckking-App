package com.example.himalayantracker

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import java.io.IOException

class TracksAdapter(val context : Activity, val productArrayList : List<Track>) :
    RecyclerView.Adapter<TracksAdapter.MyViewHolder> () {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.tracks_list , parent , false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position]

        holder.title.text = "Track Name : ${currentItem.name}"
        // holder.image.setImageResource(currentItem)
        // third party library picasso
        Picasso.get().load(currentItem.image).into(holder.image);
        holder.distance.text = "Track Distance : ${currentItem.distance_km}"
        holder.starting.text = "Starting Point : ${currentItem.starting_point}"
        holder.ending.text = "Ending Point : ${currentItem.ending_point}"
        holder.days.text = "Days To Complete this Track is : ${currentItem.duration_days}"
        holder.Location.text = "Track Point : ${currentItem.name}"
        holder.Location.setOnClickListener{
            openGoogleMaps(currentItem.name.toString())
        }


    }

    private fun openGoogleMaps(locationName: String) {
        val geocoder = Geocoder(context)
        try {
            val addresses = geocoder.getFromLocationName(locationName, 1)
            if (addresses!!.isNotEmpty()) {
                val latitude = addresses[0].latitude
                val longitude = addresses[0].longitude
                val uri = "geo:$latitude,$longitude"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                intent.setPackage("com.google.android.apps.maps")
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
            }
        } catch (e: IOException) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title : TextView
        var image : ShapeableImageView
        var distance : TextView
        var Location : Button
        var starting : TextView
        var ending : TextView
        var days : TextView
        init{
            title = itemView.findViewById(R.id.tra_productTitle)
            image = itemView.findViewById(R.id.tra_productImage)
            distance = itemView.findViewById(R.id.tra_distanceText)
            Location = itemView.findViewById(R.id.tra_locationButton)
            starting = itemView.findViewById(R.id.tra_starting)
            ending = itemView.findViewById(R.id.tra_ending)
            days = itemView.findViewById(R.id.tra_days)
        }
    }
}