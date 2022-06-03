package com.example.mvinews.presentation.news

import com.example.mvinews.domain.news.NewsData
import com.example.mvinews.domain.news.NewsEntity

sealed class NewsViewStates {
    object loading:NewsViewStates()
    data class success(val newsList:List<NewsData>):NewsViewStates()
    data class error(val message:String):NewsViewStates()
}