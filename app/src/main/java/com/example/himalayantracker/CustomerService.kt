    package com.example.himalayantracker

    import android.content.Intent
    import android.net.Uri
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Button
    import android.widget.EditText
    import android.widget.LinearLayout
    import android.widget.TextView
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.example.himalayantracker.data.Message
    import com.example.himalayantracker.ui.MessagingAdapter
    import com.example.himalayantracker.utlis.BotResponse
    import com.example.himalayantracker.utlis.Constants.OPEN_GOOGLE
    import com.example.himalayantracker.utlis.Constants.OPEN_SEARCH
    import com.example.himalayantracker.utlis.Constants.RECEIVE_ID
    import com.example.himalayantracker.utlis.Constants.SEND_ID
    import com.example.himalayantracker.utlis.Time
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.GlobalScope
    import kotlinx.coroutines.delay
    import kotlinx.coroutines.launch
    import kotlinx.coroutines.withContext

    class CustomerService : AppCompatActivity() {

        private lateinit var etmessage : EditText
        private lateinit var rvmessage : RecyclerView
        private lateinit var sendbutton : Button
        private lateinit var adapter : MessagingAdapter
        private val botList  =  listOf("peter","francesca","Luigi","Igor")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_customer_service)
            rvmessage = findViewById(R.id.rv_messages)
            etmessage = findViewById(R.id.et_message)
            sendbutton = findViewById(R.id.btn_send)
            recycleview()
            clickEvents()
            val random =  (0..3).random()
            customBotMessage("Hello! Today you're speaking with ${botList[random]},How may I help?")


        }

        private fun clickEvents() {
            sendbutton.setOnClickListener {
                sendMessage()
            }
            etmessage.setOnClickListener {
                GlobalScope.launch{
                    delay(1000)
                    withContext(Dispatchers.Main){
                        rvmessage.scrollToPosition(adapter.itemCount-1)
                    }
                }
            }

        }
        private fun sendMessage(){
            val message = etmessage.text.toString()
            val timestamp = Time.timestamp()
            if(message.isNotEmpty()){
                etmessage.setText("")
                adapter.insertMessage(Message(message, SEND_ID , timestamp))
                rvmessage.scrollToPosition(adapter.itemCount-1)
                botResponse(message)
            }
        }

        private fun botResponse(message: String) {

            val timestamp = Time.timestamp()
            GlobalScope.launch {
                delay(1000)
                withContext(Dispatchers.Main){
                    val response  = BotResponse.basicResponses(message)
                    adapter.insertMessage(Message(response, RECEIVE_ID,timestamp))

                    when(response){
                        OPEN_GOOGLE ->{
                            val site = Intent(Intent.ACTION_VIEW)
                            site.data = Uri.parse("https://www.google.com/")
                            startActivity(site)
                        }
                        OPEN_SEARCH ->{
                            val site = Intent(Intent.ACTION_VIEW)
                            val searchTerm : String? = message.substringAfter("search")
                            site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                            startActivity(site)
                        }
                    }
                }
            }
        }

        override fun onStart() {
            super.onStart()
            GlobalScope.launch {
                delay(1000)
                withContext(Dispatchers.Main){
                    rvmessage.scrollToPosition(adapter.itemCount-1)

                }
            }
        }

        private fun recycleview() {
            adapter = MessagingAdapter()
            rvmessage.adapter = adapter
            rvmessage.layoutManager = LinearLayoutManager(applicationContext)
        }


        private fun customBotMessage(message: String){
            GlobalScope.launch{
                delay(1000)
                withContext(Dispatchers.Main){
                    val timestamp = Time.timestamp()
                    adapter.insertMessage(Message(message , RECEIVE_ID , timestamp))
                    rvmessage.scrollToPosition(adapter.itemCount-1)

                }
            }
        }

    }