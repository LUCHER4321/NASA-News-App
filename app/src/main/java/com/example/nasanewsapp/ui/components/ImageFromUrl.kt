package com.example.nasanewsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageFromUrl(
    modifier: Modifier = Modifier,
    source: String,
){
    var imageSize by remember { mutableStateOf(IntSize.Zero) }
    Image(
        painter = rememberAsyncImagePainter(source),
        contentDescription = null,
        modifier = modifier
            .onSizeChanged {
                imageSize = it
                println("Image size: ${imageSize.width} x ${imageSize.height}")
            },
    )
}