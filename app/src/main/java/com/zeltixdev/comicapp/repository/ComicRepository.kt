package com.zeltixdev.comicapp.repository

import com.zeltixdev.comicapp.entity.Comic
import com.zeltixdev.comicapp.networking.Resource
import kotlinx.coroutines.flow.Flow

interface ComicRepository {
    fun getComics(): Flow<Resource<List<Comic>>>
    fun getComicDetail(comicId: String): Flow<Comic>
}