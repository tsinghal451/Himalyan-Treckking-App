package com.example.himalayantracker

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class BookingDetails : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_details)

        sharedPreferences = getSharedPreferences("BookingDetails", Context.MODE_PRIVATE)
        val paymentId = intent.getStringExtra("paymentId")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")

//        savePaymentDetails(paymentId, email, phone)
        val textEmail = findViewById<TextView>(R.id.text_email)
        val textPhone = findViewById<TextView>(R.id.text_phone)
        val textpayment = findViewById<TextView>(R.id.text_paymentId)
        textEmail.text = "Email: $email"
        textPhone.text = "Phone: $phone"
        textpayment.text = "Payment ID: $paymentId"

    }

    private fun savePaymentDetails(paymentId: String?, email: String?, phone: String?) {
        val editor = sharedPreferences.edit()
        editor.putString("paymentId", paymentId)
        editor.putString("email", email)
        editor.putString("phone", phone)
        editor.apply()
    }
}
