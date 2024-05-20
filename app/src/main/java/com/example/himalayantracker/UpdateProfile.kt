package com.example.himalayantracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class UpdateProfile : AppCompatActivity() {

    private lateinit var nameuser : TextView
    private lateinit var emailuser : TextView
    private lateinit var phnouser : TextView
    private lateinit var cityadduser : TextView
    private lateinit var updatebutton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

//        val fname = intent.getStringExtra("fname")
//        val cname = intent.getStringExtra("cname")
//        val phno = intent.getStringExtra("phno")
//        val email = intent.getStringExtra("email")

        nameuser = findViewById(R.id.pr_txName)
        emailuser = findViewById(R.id.pr_txEmail)
        phnouser = findViewById(R.id.pr_txPhoneNumber)
        cityadduser = findViewById(R.id.pr_txCity)
        updatebutton = findViewById(R.id.updateProfileButton)

        val sharedPreferences = getSharedPreferences("MyPre", Context.MODE_PRIVATE)
        val getfullname = sharedPreferences.getString("01","")
        val getcity  = sharedPreferences.getString("02","")
        val getmobno = sharedPreferences.getString("03","")
        val getemail = sharedPreferences.getString("04","")

        updatebutton.setOnClickListener{
            val fName  = nameuser.text.toString()
            val lName = cityadduser.text.toString()
            val ph  = phnouser.text.toString()
            val email =  emailuser.text.toString()

            val editor  = sharedPreferences.edit()
            editor.putString("01",fName )
            editor.putString("02",lName)
            editor.putString("03",ph)
            editor.putString("04",email)
            editor.apply()

            val intent = Intent(this@UpdateProfile,DashBoard::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()

        }



//        val updateProfileButton = findViewById<Button>(R.id.updateProfileButton)
//        updateProfileButton.setOnClickListener {
//            // Call a function to handle profile update
//            updateProfile()
//        }

    }


//    private fun updateProfile() {
//
//
//
//
//    // Retrieve data from EditTexts
////        val name = nameuser.text.toString()
////        val city = emailuser.text.toString()
////        val phoneNumber = phnouser.text.toString()
////        val email = cityadduser.text.toString()
////
////        val editor  = sharedPreferences.edit()
////        editor.putString("01",name )
////        editor.putString("02",city)
////        editor.putString("03",phoneNumber)
////        editor.putString("04",email)
////        editor.apply()
////
////        val intent = Intent(this@UpdateProfile,DashBoard::class.java)
////        startActivity(intent)
////        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
//
//    }
}