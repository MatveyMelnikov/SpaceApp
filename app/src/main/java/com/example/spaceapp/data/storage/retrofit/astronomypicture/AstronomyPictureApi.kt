package com.example.spaceapp.data.storage.retrofit.astronomypicture

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AstronomyPictureApi {
    @GET("/planetary/apod")
    suspend fun getPicture(@Query("api_key") key: String): Response<AstronomyPictureRF>
}