package com.example.spaceapp.domain.repository

import android.graphics.drawable.Drawable
import com.example.spaceapp.domain.model.AstronomyPictureParam

interface AstronomyPictureRepository {
    suspend fun loadPucture(param: AstronomyPictureParam) : Drawable?
    fun loadExplanation(param: AstronomyPictureParam) : String
}