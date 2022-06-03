package com.example.mvinews.data.remote

import com.example.mvinews.data.remote.news.NewsRepositoryImp
import com.example.mvinews.domain.news.NewsRepository
import com.intuit.sdp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(com.example.mvinews.BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideNews(apiService: ApiService): NewsRepository =
        NewsRepositoryImp(
            apiService
        )

    @Provides
    @Singleton
    @Named("api_token")
    fun provideToken() = com.example.mvinews.BuildConfig.API_KEY
}
