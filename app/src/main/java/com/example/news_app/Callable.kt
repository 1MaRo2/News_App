package com.example.news_app

import retrofit2.Call
import retrofit2.http.GET

interface Callable {
    @GET("/v2/top-headlines?country=us&apiKey=e0a5163353624aa28c5df1dcbdb416f3")
    fun getNews(): Call<News>

}