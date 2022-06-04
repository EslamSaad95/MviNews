package com.example.mvinews.domain.news

import com.example.mvinews.domain.Result

interface NewsRepository {

    suspend fun getNews(token: String, keyWord: String): Result<NewsEntity>
}