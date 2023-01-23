package com.example.testtaskfore.data.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoLocalDataSource @Inject constructor(
    private val database: AppDatabase
) {

    fun getAllPhotos(): Flow<List<PhotosEntity>> =
        database.photosDao().getAllPhotos()

    fun getAllFavoritePhotos(isLiked: Boolean): Flow<List<PhotosEntity>> =
        database.photosDao().getAllFavoritePhotos(isLiked)

    fun saveLikesInDatabase(id: String, isLiked: Boolean) =
        database.photosDao().saveLikesInDatabase(id, isLiked)

    fun insertAll(photos: List<PhotosEntity>) =
        database.photosDao().insertAll(photos)

    fun deleteAllPhotos() =
        database.photosDao().deleteAllPhotos()
}