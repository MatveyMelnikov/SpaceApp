package com.example.spaceapp.domain.usecase

import com.example.spaceapp.domain.model.AstronomyPicture
import com.example.spaceapp.domain.model.AstronomyPictureParam
import com.example.spaceapp.domain.repository.AstronomyPictureRepository

class OpenAstronomyPictureUseCase(private val repository: AstronomyPictureRepository) {
    suspend fun execute(param: AstronomyPictureParam) : AstronomyPicture? {
        if (!param.isCorrect())
            throw IllegalArgumentException()
        
        return repository.loadPucture(param)
    }
}