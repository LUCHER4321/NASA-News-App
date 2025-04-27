package com.example.nasanewsapp.ui.viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nasanewsapp.mapper.formatter
import com.example.nasanewsapp.ui.components.IconButton
import com.example.nasanewsapp.ui.components.ImageFromUrl

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    val source = viewModel.dataVisualizerState.new?.let { if(viewModel.dataVisualizerState.hd) it.hdurl else it.url } ?: ""
    println("Image Source: $source")
    LazyColumn(modifier = modifier) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(icon = Icons.Filled.ArrowBack) { viewModel.changeDay(-1) }
                IconButton(icon = Icons.Filled.ArrowForward) { viewModel.changeDay(1) }
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Checkbox(checked = viewModel.dataVisualizerState.hd, onCheckedChange = { viewModel.setHD(it) })
                Text(text = "HD")
            }
        }
        item {
            Text(
                text = viewModel.dataVisualizerState.new?.title ?: "",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
        }
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = formatter.format(viewModel.dataVisualizerState.date ?: viewModel.today()),
                textAlign = TextAlign.End
            )
        }
        item {
            ImageFromUrl(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                source = source
            )
        }
        item {
            Text(
                text = viewModel.dataVisualizerState.new?.explanation ?: "",
                textAlign = TextAlign.Justify
            )
        }
    }
}