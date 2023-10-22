package com.example.spaceapp.data.storage.retrofit.asteroidinfo

data class AsteroidInfoRF(
    val element_count: Int,
    val links: Links,
    //val near_earth_objects: NearEarthObjects
    val near_earth_objects: Map<String, List<Day>>
)