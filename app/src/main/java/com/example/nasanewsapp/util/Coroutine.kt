package com.example.nasanewsapp.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

fun coroutine(function: suspend () -> Unit){
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    coroutineScope.launch {
        function()
        coroutineScope.cancel()
    }
}