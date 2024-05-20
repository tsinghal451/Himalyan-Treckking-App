package com.example.himalayantracker

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    // step 2
    @GET("Hotels")
    fun getHotelList() : Call<MyHotelApi>

}