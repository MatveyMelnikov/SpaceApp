package com.example.spaceapp.data.storage

import com.example.spaceapp.data.storage.model.AsteroidInfoData
import com.example.spaceapp.data.storage.model.AsteroidInfoParamData

interface AsteroidInfoStorage {
    fun load(param: AsteroidInfoParamData): AsteroidInfoData
}