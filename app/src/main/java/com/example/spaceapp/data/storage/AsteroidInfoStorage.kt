package com.example.spaceapp.data.storage

import com.example.spaceapp.data.storage.model.AsteroidInfoData
import com.example.spaceapp.data.storage.model.AsteroidInfoParamData

interface AsteroidInfoStorage {
    suspend fun load(param: AsteroidInfoParamData): List<AsteroidInfoData>?
}