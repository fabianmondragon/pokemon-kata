package com.example.kata_pokemon.utils

import androidx.compose.runtime.Composable
import coil.compose.rememberImagePainter

@Composable
fun LoadImageFromUrl(url: String) =
    rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
        }
    )

