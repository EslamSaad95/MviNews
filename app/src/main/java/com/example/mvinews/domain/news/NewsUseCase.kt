package com.example.mvinews.domain.news

import android.util.Log
import com.example.mvinews.data.remote.news.NewsDto
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class NewsUseCase @Inject constructor (private val newsRepo:NewsRepository) {
    suspend fun getNews(token:String,keyWord: String): NewsEntity {
        return runCatching {newsRepo.getNews(token, keyWord) }
            .onFailure { throw RuntimeException(newsRepo.getNews(token, keyWord).message.toString()) }
            .getOrThrow()
    }





}