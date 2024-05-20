package com.example.himalayantracker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {


    private lateinit var auth : FirebaseAuth
    private lateinit var firstname : EditText
    private lateinit var lastname : EditText
    private lateinit var phno : EditText
    private lateinit var emailId : EditText
    private lateinit var submit : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        firstname = findViewById(R.id.ur_first_name)
        lastname  = findViewById(R.id.ur_last_name)
        phno = findViewById(R.id.ur_phone)
        emailId  = findViewById(R.id.ur_email_id)
        submit  = findViewById(R.id.ur_continue)



        val sharedPreferences = getSharedPreferences("MyPre",Context.MODE_PRIVATE)
        val getfullname = sharedPreferences.getString("01","")
        val getcity  = sharedPreferences.getString("02","")
        val getmobno = sharedPreferences.getString("03","")
        val getemail = sharedPreferences.getString("04","")
        if(getfullname!= "" && getcity!="" && getmobno!="" && getemail!=""){
            val intent = Intent(this@Register,DashBoard::class.java)
            startActivity(intent)
            finish()
        }

        submit.setOnClickListener{
            val fName  = firstname.text.toString()
            val lName = lastname.text.toString()
            val ph  = phno.text.toString()
            val email =  emailId.text.toString()

            val editor  = sharedPreferences.edit()
            editor.putString("01",fName )
            editor.putString("02",lName)
            editor.putString("03",ph)
            editor.putString("04",email)
            editor.apply()

            val intent = Intent(this@Register,DashBoard::class.java)
            startActivity(intent)
            finish()

        }


    }

//    override fun onStart() {
//        super.onStart()
//        if(auth.currentUser != null)
//        {
//            startActivity(Intent(this,DashBoard::class.java))
//        }
//    }

}