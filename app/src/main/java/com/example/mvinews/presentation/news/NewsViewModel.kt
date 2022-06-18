package com.example.mvinews.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvinews.domain.Result
import com.example.mvinews.domain.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class NewsViewModel @Inject constructor(
    val newsUseCase: NewsUseCase,
    @Named("api_token") val apiToken: String = ""
) : ViewModel() {

    val channelIntent = Channel<NewsIntents>(Channel.UNLIMITED)
    private val _viewState = MutableStateFlow<NewsViewStates>(NewsViewStates.loading)
    val state: StateFlow<NewsViewStates> get() = _viewState


    init {
        processIntents()
    }

    //process
    private fun processIntents() {
        viewModelScope.launch {
            channelIntent.consumeAsFlow().collect {
                when (it) {
                    is NewsIntents.initialize -> {
                        getNews()
                    }
                }
            }
        }
    }


    //reduce
    fun getNews(keyWord: String = "q") {
        viewModelScope.launch {
            _viewState.value = NewsViewStates.loading

            when (val result = newsUseCase.getNews(apiToken, "q")) {
                is Result.Success -> {
                    _viewState.value = NewsViewStates.success(result.data.newsList)
                }
                is Result.Error -> {
                    _viewState.value = NewsViewStates.error(result.error)
                }
            }

        }


    }

}