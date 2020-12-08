package com.zeltixdev.comicapp.data.local.dao

import androidx.room.*
import com.zeltixdev.comicapp.entity.Comic
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicDao {

    @Query("SELECT * FROM comics")
    fun getAllComics() : Flow<List<Comic>>

    @Query("SELECT * FROM comics WHERE id = :id")
    fun getComic(id : String) : Flow<Comic>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(comics: List<Comic>)
}