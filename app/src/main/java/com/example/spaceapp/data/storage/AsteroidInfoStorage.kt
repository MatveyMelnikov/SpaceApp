package com.example.spaceapp.data.storage

import com.example.spaceapp.data.storage.model.AsteroidInfoData
import com.example.spaceapp.data.storage.model.AsteroidInfoParamData
import java.time.LocalDate

interface AsteroidInfoStorage {
    suspend fun load(param: AsteroidInfoParamData): List<AsteroidInfoData>?
    suspend fun save(date: LocalDate, data: List<AsteroidInfoData>)
}