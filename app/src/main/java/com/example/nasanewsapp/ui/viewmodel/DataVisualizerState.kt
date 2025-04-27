package com.example.nasanewsapp.ui.viewmodel

import com.example.nasanewsapp.domain.New
import java.util.Date

data class DataVisualizerState(
    val date: Date? = null,
    val new: New? = null,
    val hd: Boolean = false,
)
