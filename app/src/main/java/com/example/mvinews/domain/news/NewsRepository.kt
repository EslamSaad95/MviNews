package com.example.mvinews.domain.news

import com.example.mvinews.data.remote.news.NewsDto

interface NewsRepository {

    suspend fun getNews(token:String,keyWord:String): NewsEntity
}