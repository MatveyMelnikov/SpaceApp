package com.example.spaceapp.data.storage.retrofit.asteroidinfo

import com.example.spaceapp.data.storage.retrofit.astronomypicture.AstronomyPictureRF
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidInfoApi {
    @GET("/neo/rest/v1/feed")
    suspend fun getAsteroidInfo(
        @Query("api_key") key: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): Response<AsteroidInfoRF>
}