package com.example.news_app


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.news_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadNews()
        binding.refresh.setOnRefreshListener { loadNews() }
    }

    private fun loadNews() {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cat = intent.getStringExtra("cat")
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val code = prefs.getString("code", "us")
        val callable = retrofit.create(Callable::class.java)
        callable.getNews(cat!!, code!!).enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                val articles = news?.articles
                Log.d("trace", "Link ${articles?.get(0)?.urlToImage}")
                val adapter = NewsAdapter(this@MainActivity, articles!!)
                binding.newsRv.adapter = adapter
                binding.progress.visibility = View.GONE
                binding.refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("trace", "Error: ${t.localizedMessage}")
                binding.progress.visibility = View.GONE
                binding.refresh.isRefreshing = false
            }

        })
    }
}