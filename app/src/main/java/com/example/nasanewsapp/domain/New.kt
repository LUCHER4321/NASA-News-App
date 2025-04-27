package com.example.nasanewsapp.domain

import java.util.Date

data class New(
    val date: Date,
    val explanation: String,
    val hdurl: String,
    val title: String,
    val url: String,
)
