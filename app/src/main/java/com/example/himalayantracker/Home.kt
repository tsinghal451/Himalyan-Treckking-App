package com.example.himalayantracker


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth


class Home : Fragment() {
    private lateinit var auth : FirebaseAuth
    private lateinit var hotels : LinearLayout
    private lateinit var tracks : LinearLayout
    private lateinit var booking : LinearLayout
    private lateinit var news : LinearLayout
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        auth = FirebaseAuth.getInstance()
        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("BookingDetails", Context.MODE_PRIVATE)

        hotels = view.findViewById(R.id.book_hotels)
        hotels.setOnClickListener {
            val intent = Intent(requireContext(),HotelsList::class.java)
            startActivity(intent)
        }


        tracks = view.findViewById(R.id.tracks_details)
        tracks.setOnClickListener {
            val intent = Intent(requireContext(),Tracks::class.java)
            startActivity(intent)
        }

        news = view.findViewById(R.id.tracks_news)
        news.setOnClickListener {
            val intent = Intent(requireContext(),News::class.java)
            startActivity(intent)
        }

        booking = view.findViewById(R.id.book_details)
        booking.setOnClickListener {


//
            val editor = sharedPreferences.edit()
            editor.putString("paymentId", "")
            editor.putString("email", "")
            editor.putString("phone", "")
            editor.apply()
//            val paymentId = sharedPreferences.getString("paymentId", "")
//            val email = sharedPreferences.getString("email", "")
//            val phone = sharedPreferences.getString("phone", "")

            // You can use the retrieved data as needed here

            // For example, start BookingDetails activity with the retrieved data
            val intent = Intent(requireContext(), BookingDetails::class.java)
            intent.putExtra("paymentId", "")
            intent.putExtra("email", "")
            intent.putExtra("phone", "")
            startActivity(intent)


        }



        return view
    }

}