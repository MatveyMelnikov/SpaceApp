package com.example.spaceapp.domain.repository

import com.example.spaceapp.data.storage.model.AstronomyPictureData
import com.example.spaceapp.domain.model.AstronomyPicture
import com.example.spaceapp.domain.model.AstronomyPictureParam

interface AstronomyPictureRepository {
    suspend fun loadPucture(param: AstronomyPictureParam) : AstronomyPicture?
//    fun loadExplanation(param: AstronomyPictureParam) : String
}