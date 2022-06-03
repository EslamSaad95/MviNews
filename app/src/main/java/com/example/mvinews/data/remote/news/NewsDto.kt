package com.example.mvinews.data.remote.news

import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("status")
    val status:String,
    @SerializedName("message")
    val message:String,
    @SerializedName("totalResults")
    val totalResults:Int,
    @SerializedName("articles")
    val articles:List<ArticleData>

)

data class ArticleData(
    @SerializedName("author")
    val author:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("url")
    val url:String,
    @SerializedName("urlToImage")
    val urltoImage:String,
    @SerializedName("publishedAt")
    val publishedAt:String,
    @SerializedName("content")
    val contenct:String,
    @SerializedName("source")
    val source:Source

)
data class Source(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name:String
)
