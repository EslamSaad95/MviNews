package com.example.mvinews.data.remote

import com.example.mvinews.data.remote.news.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getNews(
        @Query("apiKey") apiKey:String,
        @Query("q")keyWord:String
    ): NewsDto
}