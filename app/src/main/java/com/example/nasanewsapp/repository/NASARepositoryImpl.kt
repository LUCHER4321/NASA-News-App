package com.example.nasanewsapp.repository

import com.example.nasanewsapp.domain.New
import com.example.nasanewsapp.mapper.formatter
import com.example.nasanewsapp.mapper.toNew
import com.example.nasanewsapp.remote.NASARestAPI
import com.example.nasanewsapp.remote.NewDto
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NASARepositoryImpl @Inject constructor(
    private val api: NASARestAPI,
) : NASARepository {
    override suspend fun getNew(date: Date?): New? {
        var rb: ResponseBody? = null
        var remoteRead = false
        while (!remoteRead){
            try {
                rb = if(date != null) api.getNewByDate(date = formatter.format(date)) else api.getNew()
                remoteRead = true
            } catch (e: Exception){
                println("Remote Read Error: $e")
            }
        }
        val dto = toDto(rb)
        return dto?.toNew()
    }

    fun toDto(responseBody: ResponseBody?): NewDto?{
        return try {
            responseBody?.source()?.let { source ->
                val jsonReader = JsonReader.of(source)
                jsonReader.isLenient = true
                val dto = dtoAdapter.fromJson(jsonReader)
                println("Parsed DTO: $dto")
                dto
            }
        } catch (e: Exception){
            println("DTO Error: $e")
            null
        }
    }

    companion object{
        val moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val dtoAdapter = moshi.adapter(NewDto::class.java)
    }
}