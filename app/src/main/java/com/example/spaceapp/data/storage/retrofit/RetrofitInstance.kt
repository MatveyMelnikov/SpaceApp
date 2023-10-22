package com.example.spaceapp.data.storage.retrofit

import com.example.spaceapp.data.storage.retrofit.asteroidinfo.AsteroidInfoApi
import com.example.spaceapp.data.storage.retrofit.astronomypicture.AstronomyPictureApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val astronomyPictureApi: AstronomyPictureApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.nasa.gov")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AstronomyPictureApi::class.java)
    }
    val asteroidInfoApi: AsteroidInfoApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.nasa.gov")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AsteroidInfoApi::class.java)
    }
}