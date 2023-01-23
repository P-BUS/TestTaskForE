package com.example.testtaskfore.data.network

import com.example.testtaskfore.data.model.PhotoResponse
import com.example.testtaskfore.data.model.UnsplashPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {
    @GET(ENDPOINT)
    suspend fun getPhotos(
        @Query("client_id") clientId: String = ACCESS_KEY,
        @Query("page") pageNumber: Int = 3,
        @Query("per_page") itemsPerPage: Int = 30,
        @Query("order_by") sort: String = "popular"
    ): Response<List<UnsplashPhoto>>

    @GET(SEARCH_ENDPOINT)
    suspend fun getSearchPhotos(
        @Query("query") searchQuery: String = "space",
        @Query("client_id") clientId: String = ACCESS_KEY,
        @Query("page") pageNumber: Int = 3,
        @Query("per_page") itemsPerPage: Int = 30,
        @Query("order_by") sort: String = "popular"
    ): Response<PhotoResponse>


    companion object {
        const val ENDPOINT = "photos"
        const val SEARCH_ENDPOINT = "search/photos"
        const val ACCESS_KEY = "cVHLHASj27DThAPTGCetJ3wj-1evc8lLk6dKF7TWAqE"
        const val SECRET_KEY = "ZWEO8kwAEaQd74OFzq9HKxsqx3wwfv5hgL2efGaVr90"
    }

}