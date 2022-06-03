package com.example.mvinews.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvinews.data.remote.news.ArticleData
import com.example.mvinews.data.remote.news.NewsDto
import com.example.mvinews.domain.news.NewsData
import com.example.mvinews.domain.news.NewsEntity
import com.example.mvinews.domain.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class NewsViewModel @Inject constructor(
    val newsUseCase: NewsUseCase,
    @Named("api_token") val apiToken: String
) : ViewModel() {

     val channelIntent=Channel<NewsIntents>(Channel.UNLIMITED)
    private val _viewState = MutableStateFlow<NewsViewStates>(NewsViewStates.loading)
    val state: StateFlow<NewsViewStates> get() = _viewState


    init {
        processIntents()
    }
    //process
    private fun processIntents() {
        viewModelScope.launch {
            channelIntent.consumeAsFlow().collect {
                when(it)
                {
                    is NewsIntents.initialize->{getNews()}
                }
            }
        }
    }


    //reduce
    private fun getNews(keyWord: String = "q") {
        viewModelScope.launch {
            _viewState.value=NewsViewStates.loading
            try {
                _viewState.value=NewsViewStates.success(newsUseCase.getNews(apiToken,"q").newsList)
            } catch (e: Exception) {
                e.printStackTrace()
                when (e) {
                    is HttpException -> _viewState.value = NewsViewStates.error("Http Exception")
                    is IOException -> _viewState.value = NewsViewStates.error("No Internet Connection")
                    else -> {
                        _viewState.value=NewsViewStates.error(e.localizedMessage?:"")
                    }
                }

            }

        }
    }
}