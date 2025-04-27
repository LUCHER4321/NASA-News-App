package com.example.nasanewsapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.nasanewsapp.repository.NASARepository
import com.example.nasanewsapp.util.coroutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.time.Instant
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val repository: NASARepository
) : ViewModel() {
    private val daysToMillis = 1000 * 60 * 60 * 24
    var dataVisualizerState by mutableStateOf(DataVisualizerState())
        private set

    init {
        updateNew()
    }

    private fun updateNew(){
        coroutine {
            kotlinx.coroutines.withContext(Dispatchers.Main){
                val myNew = repository.getNew(dataVisualizerState.date)
                dataVisualizerState = dataVisualizerState.copy(new = myNew)
            }
        }
    }

    fun today(): Date{
        return Date.from(Instant.now())
    }

    fun changeDay(plusDays: Int){
        val todayLong = today().time
        val date = dataVisualizerState.date ?: today()
        val newDate = if(date.time + plusDays >= todayLong) null else Date(date.time + plusDays * daysToMillis)
        dataVisualizerState = dataVisualizerState.copy(date = newDate)
        updateNew()
    }

    fun setHD(hd: Boolean){
        dataVisualizerState = dataVisualizerState.copy(hd = hd)
    }
}