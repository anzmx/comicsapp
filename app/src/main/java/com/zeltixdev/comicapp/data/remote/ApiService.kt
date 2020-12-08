package com.zeltixdev.comicapp.data.remote

import com.zeltixdev.comicapp.entity.ComicDataWrapper
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v1/public/comics?limit=50")
    suspend fun getComics(): Response<ComicDataWrapper>
}