package com.example.nasanewsapp.di

import com.example.nasanewsapp.NASANewsApp
import com.example.nasanewsapp.remote.NASARestAPI
import com.example.nasanewsapp.repository.NASARepository
import com.example.nasanewsapp.repository.NASARepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRestAPI(): NASARestAPI{
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(NASARestAPI.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(NASARepositoryImpl.moshi))
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideApp(): NASANewsApp{
        return NASANewsApp()
    }

    @Provides
    @Singleton
    fun provideRepository(api: NASARestAPI): NASARepository{
        return NASARepositoryImpl(api)
    }
}