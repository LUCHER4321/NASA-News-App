package com.example.nasanewsapp.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconButton(
    icon: ImageVector,
    onClick: () -> Unit,
){
    Button(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}