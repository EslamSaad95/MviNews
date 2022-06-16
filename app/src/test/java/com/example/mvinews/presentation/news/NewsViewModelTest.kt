package com.example.mvinews.presentation.news

import com.example.mvinews.domain.Result
import com.example.mvinews.domain.news.NewsEntity
import com.example.mvinews.domain.news.NewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class NewsViewModelTest {
    @Rule
    @JvmField
    val rule = MainCoroutineRule()

    @Mock
    private lateinit var newsUseCase: NewsUseCase

    @Mock
    private lateinit var viewModel: NewsViewModel

    @Before
    fun setup() {
        viewModel = NewsViewModel(newsUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @Test
    fun getNews_withWrongParams_ThenEmitErrorViewState() {
        runBlocking {
            Mockito.`when`(newsUseCase.getNews("", "q")).thenAnswer { Result.Error<String>("") }
            viewModel.channelIntent.send(NewsIntents.initialize)
            assert(viewModel.state.value is NewsViewStates.error)
        }
    }

    @Test
    fun getNews_withRightParams_thenEmitSuccessViewState() {
        runBlocking {
            Mockito.`when`(newsUseCase.getNews("", "q"))
                .thenAnswer { Result.Success(NewsEntity("", "", emptyList())) }
            viewModel.channelIntent.send(NewsIntents.initialize)

            assert(viewModel.state.value is NewsViewStates.success)
            //     assertEquals(viewModel.state.value, NewsViewStates.success(emptyList()))


        }
    }

    @Test
    fun processIntent_withInitialize_ThenCallGetNewsFunc() {
        runBlocking {
            Mockito.`when`(newsUseCase.getNews("", "q"))
                .thenAnswer { Result.Success(NewsEntity("", "", emptyList())) }
            viewModel.channelIntent.send(NewsIntents.initialize)
            assertNotNull(viewModel.getNews(""))

        }
    }


}