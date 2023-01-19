package com.example.testtaskfore.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos_database")
    fun getAllPhotos(): Flow<List<PhotosEntity>>

    @Query("SELECT * FROM photos_database WHERE id = :id")
    fun getBook(id: String): Flow<PhotosEntity>

    @Query("UPDATE photos_database SET liked_by_user = :isLiked WHERE id = :id")
    fun saveLikesInDatabase(id: String, isLiked: Boolean)

    @Upsert
    fun insertAll(photos: List<PhotosEntity>)
}