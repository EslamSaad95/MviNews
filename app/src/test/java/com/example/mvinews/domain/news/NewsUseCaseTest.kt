package com.example.mvinews.domain.news

import com.example.mvinews.domain.Result
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsUseCaseTest {


    @Mock
    private lateinit var newsRepo:NewsRepository

    @Test(expected = Exception::class)
    fun getNews_WithWrongParams_ThenRunException() {
        val repo = object : NewsRepository {
            override suspend fun getNews(token: String, keyWord: String): Result<NewsEntity> {
                throw Exception()
            }

        }

        val useCase = NewsUseCase(repo)
        runBlocking {
            assert(useCase.getNews("", "q") is Result.Error)
        }
    }


    @Test
    fun getNews_WithRightParams_ThenReturnNewsEntity() {
        //act
        val newsEntity = NewsEntity("", "", emptyList())

        //arrange
        val repo = object : NewsRepository {
            override suspend fun getNews(token: String, keyWord: String): Result<NewsEntity> {
                return Result.Success(NewsEntity("", "", emptyList()))
            }
        }
        val useCase=NewsUseCase(repo)
        runBlocking { assert(useCase.getNews("", "q") is Result.Success) }


    }


    @Test(expected = Exception::class)
    fun getNews_WithWrongParams_ThenReturnException_UsingMockito()
    {
       runBlocking {
           Mockito.`when`(newsRepo.getNews("","q")).thenAnswer { throw Exception() }}
        val useCasee = NewsUseCase(newsRepo)
        runBlocking { useCasee.getNews("","q") }

       }

    @Test
    fun getNews_WithRightParams_ThenReturnNewsEntityObj_UsingMockito()
    {
        //act
        runBlocking { Mockito.`when`(newsRepo.getNews("","q")).thenAnswer {
            Result.Success(NewsEntity("", "", emptyList()))
        } }
        val useCase=NewsUseCase(newsRepo)
        runBlocking {val expected= useCase.getNews("","q")
            assert(expected is Result.Success)
        }


    }





}