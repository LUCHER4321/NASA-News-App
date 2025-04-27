package com.example.nasanewsapp.remote

import com.example.nasanewsapp.BuildConfig
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface NASARestAPI {
    @GET("planetary/apod")
    suspend fun getNew(
        @Query("api_key") key: String = BuildConfig.API_KEY,
    ): ResponseBody

    @GET("planetary/apod")
    suspend fun getNewByDate(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("date") date: String,
    ): ResponseBody

    companion object{
        const val BASE_URL = "https://api.nasa.gov/"
    }
}