package com.example.mvinews.data.remote.news

import android.util.Log
import com.example.mvinews.data.remote.ApiService
import com.example.mvinews.domain.news.NewsEntity
import com.example.mvinews.domain.news.NewsRepository
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(private val apiService: ApiService):
NewsRepository{
    override suspend fun getNews(token: String, keyWord: String): NewsEntity {
        return apiService.getNews(token,keyWord).toNewsEntity()
    }

}