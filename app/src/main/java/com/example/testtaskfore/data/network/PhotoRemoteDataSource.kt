package com.example.testtaskfore.data.network

import com.example.testtaskfore.data.model.ApiResult
import com.example.testtaskfore.data.model.PhotoResponse
import com.example.testtaskfore.data.model.UnsplashPhoto
import javax.inject.Inject

class PhotoRemoteDataSource @Inject constructor(
    private val photoApiService: PhotoApiService
) {
    suspend fun getPhotos(): ApiResult<List<UnsplashPhoto>> =
        handleApiResponse {
            photoApiService.getPhotos()
        }

    suspend fun getSearchPhotos(): ApiResult<PhotoResponse> =
        handleApiResponse {
            photoApiService.getSearchPhotos()
        }
}