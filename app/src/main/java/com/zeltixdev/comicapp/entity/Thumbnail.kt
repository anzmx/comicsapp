package com.zeltixdev.comicapp.entity

data class Thumbnail(
    val extension: String,
    val path: String
)

fun Thumbnail.url():String{
    return "$path.$extension"
}

