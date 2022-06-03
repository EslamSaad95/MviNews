package com.example.mvinews.domain.news

data class NewsEntity(
    val status:String?=null,
    val message:String?=null,
    val newsList:List<NewsData>
)

data class NewsData(
    val author:String?=null,
    val title:String?=null,
    val description:String?=null,
    val source:String?=null
)


