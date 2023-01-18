package com.example.testtaskfore.data.network

import com.example.testtaskfore.data.model.PhotoResponse
import com.example.testtaskfore.data.model.UnsplashPhoto
import com.example.testtaskfore.utils.Constants.ACCESS_KEY
import com.example.testtaskfore.utils.Constants.ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {
    @GET(ENDPOINT)
    suspend fun getPhotos(
        @Query("client_id") clientId: String = ACCESS_KEY,
        @Query("page") pageNumber: Int = 5,
        @Query("per_page") itemsPerPage: Int = 30,
        @Query("order_by") sort: String = "popular"
    ): List<UnsplashPhoto>

}