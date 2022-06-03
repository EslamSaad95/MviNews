package com.example.mvinews.domain.news

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class NewsUseCaseTest {


    @Mock
    private lateinit var newsRepo:NewsRepository

    @Test(expected = Exception::class)
    fun getNews_WithWrongParams_ThenRunException() {
        val repo = object : NewsRepository {
            override suspend fun getNews(token: String, keyWord: String): NewsEntity {
                throw Exception()
            }

        }

        val useCase = NewsUseCase(repo)
        runBlocking { useCase.getNews("", "q") }
    }


    @Test
    fun getNews_WithRightParams_ThenReturnNewsEntity() {
        //act
        val newsEntity = NewsEntity("", "", emptyList())

        //arrange
        val repo = object : NewsRepository {
            override suspend fun getNews(token: String, keyWord: String): NewsEntity {
                return NewsEntity("", "", emptyList())
            }
        }
        val useCase=NewsUseCase(repo)
        runBlocking {  useCase.getNews("","q")}

        //assert
        assertEquals(newsEntity,NewsEntity("","", emptyList()))
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
            NewsEntity("","", emptyList())
        } }
        val useCase=NewsUseCase(newsRepo)
        runBlocking {val expected= useCase.getNews("","q")
        assertEquals(expected,NewsEntity("","", emptyList()))
        }


    }





}