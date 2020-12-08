package com.zeltixdev.comicapp.entity

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<EventSummary>,
    val returned: Int
)