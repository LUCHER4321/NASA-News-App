package com.example.nasanewsapp.repository

import com.example.nasanewsapp.domain.New
import java.util.Date

interface NASARepository {
    suspend fun getNew(date: Date? = null): New?
}