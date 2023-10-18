package com.example.spaceapp.domain.usecase

import com.example.spaceapp.domain.model.AsteroidInformation
import com.example.spaceapp.domain.model.AsteroidInformationParam
import com.example.spaceapp.domain.repository.AsteroidInformationRepository

class OpenAsteroidInformationUseCase(private val repository: AsteroidInformationRepository) {
    fun execute(param: AsteroidInformationParam) : AsteroidInformation {
        if (!param.isCorrect())
            throw IllegalArgumentException();

        return repository.loadInformation(param)
    }
}