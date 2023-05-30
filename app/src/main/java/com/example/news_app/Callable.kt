package com.example.news_app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Callable {
    @GET("/v2/top-headlines?apiKey=e0a5163353624aa28c5df1dcbdb416f3")
    fun getNews(@Query("category") cat:String,@Query("country") code:String): Call<News>

}