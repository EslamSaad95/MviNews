package com.example.mvinews.data.remote.news

import com.example.mvinews.domain.news.NewsData
import com.example.mvinews.domain.news.NewsEntity

fun NewsDto.toNewsEntity(): NewsEntity {
   val newsEntityList = ArrayList<NewsData>()
    this.articles.forEachIndexed { index, articleData ->
        newsEntityList.add( NewsData(
            author = articleData.author,
            title = articleData.title,
            description = articleData.description,
            source = articleData.source.name,
        ))
    }
    return NewsEntity(this.status,this.message,newsEntityList)
}


