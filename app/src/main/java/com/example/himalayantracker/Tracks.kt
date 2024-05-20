package com.example.himalayantracker

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Tracks : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var  myAdapter: TracksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracks)


        recyclerView = findViewById(R.id.track_recycler_view)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://hoteltracking.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Tracksinterface::class.java)

        val retrofitData = retrofitBuilder.getTrackList()

        retrofitData.enqueue(object : Callback<MyTrackApi?> {
            override fun onResponse(call: Call<MyTrackApi?>, response: Response<MyTrackApi?>) {
                val responseBody = response.body()
                val tracksList = responseBody?.tracks!!

                myAdapter = TracksAdapter(this@Tracks,tracksList)
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@Tracks)


            }

            override fun onFailure(call: Call<MyTrackApi?>, t: Throwable) {
                Log.d("TracksList", "OnFailure: " + t.message)
            }
        })

    }
}