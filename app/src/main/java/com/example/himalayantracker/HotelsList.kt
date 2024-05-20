package com.example.himalayantracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.razorpay.PaymentResultListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener


class HotelsList : AppCompatActivity() , PaymentResultWithDataListener {

    lateinit var recyclerView: RecyclerView
    lateinit var  myAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotels_list)

        recyclerView = findViewById(R.id.recycler_view)

        Checkout.preload(applicationContext)
        val co = Checkout()
        co.setKeyID("rzp_test_JIt75TRR2khxQv")
//        Checkout.sdkCheckIntegration(this@HotelsList)


        //step3
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://hoteltracking.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getHotelList()

        // step 4
        retrofitData.enqueue(object : Callback<MyHotelApi?> {
            override fun onResponse(call: Call<MyHotelApi?>, response: Response<MyHotelApi?>) {
                val responseBody = response.body()
                val hotelsList = responseBody?.hotels!!
                myAdapter = ProductAdapter(this@HotelsList,hotelsList,this@HotelsList)
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@HotelsList)



            }


            override fun onFailure(call: Call<MyHotelApi?>, t: Throwable) {
                Log.d("HotelsList", "OnFailure: " + t.message)
            }
        })

//        retrofitData.enqueue(object : Callback<MyData?> {
//            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
//                // if api call is success , then use the data of api and show in your app
//                val responseBody = response.body()
//                val productList = responseBody?.products!!
//
//                myAdapter = ProductAdapter(this@HotelsList,productList)
//                recyclerView.adapter = myAdapter
//                recyclerView.layoutManager = LinearLayoutManager(this@HotelsList)
//
////                val collectDataInStringBuilder = StringBuilder()
////
//////                if(productList != null){
//////
//////                }
////
////                for(myData in productList){
////                    collectDataInStringBuilder.append(myData.title + "")
////                }
////
////                val tv = findViewById<TextView>(R.id.api_text)
////                tv.text = collectDataInStringBuilder
//            }
//
//            override fun onFailure(call: Call<MyData?>, t: Throwable) {
//                // if api call fails
//                Log.d("HotelsList", "OnFailure: " + t.message)
//            }
//        })


    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        val paymentId = p1?.paymentId
//        val orderId = p1?.orderId
//        val signature = p1?.signature
        val email = p1?.userEmail
        val phone = p1?.userContact

//        val status = p1?.
//        val name = p1?.name
        // Assuming you have other necessary data from the payment

        // Create an Intent to start the BookingDetails activity
        val intent = Intent(this, BookingDetails::class.java)
        // Pass the payment details as extras to the intent
        intent.putExtra("paymentId", paymentId)
        intent.putExtra("email", email)
        intent.putExtra("phone", phone)
//        intent.putExtra("name", name)
//        intent.putExtra("orderId", orderId)
//        intent.putExtra("signature", signature)
        // Add other necessary data as extras to the intent

        // Start the BookingDetails activity with the intent
        startActivity(intent)
        Toast.makeText(this,"Payment Success",Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this,"Error : ${p1.toString()}",Toast.LENGTH_SHORT).show()
    }


}