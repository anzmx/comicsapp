package com.zeltixdev.comicapp.data.remote

import com.zeltixdev.comicapp.entity.ComicDataWrapper
import retrofit2.Response

interface ApiHelper {
    suspend fun getComics():Response<ComicDataWrapper>
}