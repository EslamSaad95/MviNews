package com.example.mvinews.domain.news

import com.example.mvinews.domain.Result
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val newsRepo: NewsRepository) {
    suspend fun getNews(token: String, keyWord: String): Result<NewsEntity> {
        return newsRepo.getNews(token, keyWord)
    }


}