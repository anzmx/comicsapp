package com.zeltixdev.comicapp.data.remote

import com.zeltixdev.comicapp.entity.ComicDataWrapper
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService): ApiHelper {
    override suspend fun getComics(): Response<ComicDataWrapper> = apiService.getComics()
}