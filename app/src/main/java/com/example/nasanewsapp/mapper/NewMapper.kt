package com.example.nasanewsapp.mapper

import com.example.nasanewsapp.domain.New
import com.example.nasanewsapp.remote.NewDto
import java.text.SimpleDateFormat

val formatter = SimpleDateFormat("yyyy-MM-dd")

fun NewDto.toNew(): New{
    return New(
        date = formatter.parse(this.date),
        explanation = this.explanation,
        hdurl = this.hdurl,
        title = this.title,
        url = this.url,
    )
}