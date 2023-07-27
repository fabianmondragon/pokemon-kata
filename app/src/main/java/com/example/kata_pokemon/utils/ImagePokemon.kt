package com.example.kata_pokemon.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder

@Composable
fun LoadImageFromUrl(url: String) =
    rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
        }
    )

@Composable
fun LoadSvgFromUrl(url: String) =
    rememberImagePainter(
        data = url,
        builder = {
            decoder(SvgDecoder(LocalContext.current))
        })
