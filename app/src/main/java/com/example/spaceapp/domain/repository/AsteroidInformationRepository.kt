package com.example.spaceapp.domain.repository

import com.example.spaceapp.domain.model.AsteroidInformation
import com.example.spaceapp.domain.model.AsteroidInformationParam

interface AsteroidInformationRepository {
    fun loadInformation(param: AsteroidInformationParam) : AsteroidInformation
}