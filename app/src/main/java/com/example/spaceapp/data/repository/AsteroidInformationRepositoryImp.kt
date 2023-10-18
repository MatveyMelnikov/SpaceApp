package com.example.spaceapp.data.repository

import com.example.spaceapp.domain.model.AsteroidInformation
import com.example.spaceapp.domain.model.AsteroidInformationParam
import com.example.spaceapp.domain.repository.AsteroidInformationRepository
import java.time.LocalDateTime

class AsteroidInformationRepositoryImp : AsteroidInformationRepository {
    override fun loadInformation(param: AsteroidInformationParam): AsteroidInformation {
        return AsteroidInformation(
            "2008 QV11", "Aten [NEO]",
            LocalDateTime.of(2021, 8, 21, 5, 48, 55),
            4739,
            "DE441",
            "SB441-N16",
            0
        )
    }
}