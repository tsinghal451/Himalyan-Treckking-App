package com.example.himalayantracker

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class News : AppCompatActivity() {
    private val BASE_URL = "https://gnews.io/api/v4/"
    private val API_KEY = "1260a6a7397ad3bccd56a36e8681415c"
    private val TAG = News::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val newsAPI = retrofit.create(NewsAPI::class.java)

        // Change 'us' to 'in' for India
        val call = newsAPI.getNews("trekking", API_KEY, "en", "in", "general")

        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles
                    if (articles != null) {
                        val adapter = ArticleAdapter(this@News, articles)
                        val listView = findViewById<ListView>(R.id.listViewArticles)
                        listView.adapter = adapter
                    } else {
                        Log.e(TAG, "Articles list is null")
                    }
                } else {
                    Log.e(TAG, "Failed to fetch news: " + response.message())
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch news", t)
            }
        })
    }
}
