package com.example.testtaskfore.data.repository

import android.util.Log
import com.example.testtaskfore.data.database.AppDatabase
import com.example.testtaskfore.data.model.ApiResult
import com.example.testtaskfore.data.model.UnsplashPhoto
import com.example.testtaskfore.data.network.PhotoRemoteDataSource
import com.example.testtaskfore.utils.asDatabaseModel
import com.example.testtaskfore.utils.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "PhotosRepository"

@Singleton
class PhotosRepository @Inject constructor(
    private val network: PhotoRemoteDataSource,
    private val database: AppDatabase
) {

    val photos: Flow<List<UnsplashPhoto>> =
        database.photosDao().getAllPhotos()
            .map { it.asDomainModel() }

    val favoriteFhotos: Flow<List<UnsplashPhoto>> =
        database.photosDao().getAllFavoritePhotos(true)
            .map { it.asDomainModel() }

    suspend fun refreshPhotos() {
        withContext(Dispatchers.IO) {
            // Retrieve photos from network
            var listPhotos: List<UnsplashPhoto> = listOf()
            when (val response = network.getPhotos()) {
                is ApiResult.Success -> listPhotos = response.data
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.message}")
            }
            // Update database
            database.photosDao().insertAll(listPhotos.asDatabaseModel())
        }
    }

    suspend fun refreshSearchPhotos(searchQuery: String) {
        withContext(Dispatchers.IO) {
            // Retrieve search photos from network
            var listSearchPhotos: List<UnsplashPhoto> = listOf()
            when (val response = network.getSearchPhotos(searchQuery)) {
                is ApiResult.Success -> listSearchPhotos = response.data.results
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.message}")
            }
            // Update database if the search result is success
            if (listSearchPhotos.isNotEmpty()) {
                database.photosDao().apply {
                    deleteAllPhotos()
                    insertAll(listSearchPhotos.asDatabaseModel())
                }
            }
        }
    }

    suspend fun saveLikesInDatabase(id: String, isLiked: Boolean) {
        withContext(Dispatchers.IO) {
            database.photosDao().saveLikesInDatabase(id, isLiked)
        }
    }
}