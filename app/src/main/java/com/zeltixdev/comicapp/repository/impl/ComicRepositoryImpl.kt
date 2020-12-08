package com.zeltixdev.comicapp.repository.impl

import com.zeltixdev.comicapp.data.local.dao.ComicDao
import com.zeltixdev.comicapp.data.remote.ApiHelper
import com.zeltixdev.comicapp.entity.Comic
import com.zeltixdev.comicapp.repository.ComicRepository
import com.zeltixdev.comicapp.networking.Resource
import com.zeltixdev.comicapp.networking.networkBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val comicDao: ComicDao
) : ComicRepository {

    override fun getComics(): Flow<Resource<List<Comic>>> {
        return networkBoundResource(
            query = { comicDao.getAllComics() },
            fetch = { apiHelper.getComics() },
            saveFetchResult = { comics -> comicDao.insertAll(comics.body()!!.data.results) }
        )
    }

    override fun getComicDetail(comicId: String): Flow<Comic> {
       return comicDao.getComic(comicId)
    }
}