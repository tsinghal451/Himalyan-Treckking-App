package com.example.himalayantracker

import retrofit2.Call
import retrofit2.http.GET

interface Tracksinterface {

    @GET("tracks")
    fun getTrackList() : Call<MyTrackApi>

}