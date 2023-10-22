package com.example.spaceapp.data.storage

import com.example.spaceapp.data.storage.model.AstronomyPictureData
import com.example.spaceapp.data.storage.model.AstronomyPictureDataParam

interface AstronomyPictureStorage {
    suspend fun load(param: AstronomyPictureDataParam) : AstronomyPictureData?
}