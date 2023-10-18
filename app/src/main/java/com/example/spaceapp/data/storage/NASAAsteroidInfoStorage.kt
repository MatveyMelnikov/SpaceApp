package com.example.spaceapp.data.storage

import com.example.spaceapp.data.storage.model.AsteroidInfoData
import com.example.spaceapp.data.storage.model.AsteroidInfoParamData
import java.time.LocalDateTime

class NASAAsteroidInfoStorage : AsteroidInfoStorage {
    override fun load(param: AsteroidInfoParamData): AsteroidInfoData {
        return AsteroidInfoData(
            "2008 QV11", "Aten [NEO]",
            LocalDateTime.of(2021, 8, 21, 5, 48, 55),
            4739,
            "DE441",
            "SB441-N16",
            0
        )
    }
}