package com.example.testtaskfore.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos_database")
    fun getAllPhotos(): Flow<List<PhotosEntity>>

    @Query("SELECT * FROM photos_database WHERE liked_by_user = :isLiked")
    fun getAllFavoritePhotos(isLiked: Boolean): Flow<List<PhotosEntity>>

    @Query("UPDATE photos_database SET liked_by_user = :isLiked WHERE id = :id")
    fun saveLikesInDatabase(id: String, isLiked: Boolean)

    @Upsert
    fun insertAll(photos: List<PhotosEntity>)

    @Query("DELETE FROM photos_database")
    fun deleteAllPhotos()
}