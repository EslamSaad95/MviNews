package com.example.mvinews.data.remote.news

import com.example.mvinews.data.remote.ApiService
import com.example.mvinews.data.remote.getErrorMessage
import com.example.mvinews.domain.Result
import com.example.mvinews.domain.news.NewsEntity
import com.example.mvinews.domain.news.NewsRepository
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(private val apiService: ApiService):
NewsRepository {
    override suspend fun getNews(token: String, keyWord: String): Result<NewsEntity> {

        return try {
            Result.Success(apiService.getNews(token, keyWord).toNewsEntity())
        } catch (e: Exception) {
            Result.Error(e.getErrorMessage())
        }

    }

}