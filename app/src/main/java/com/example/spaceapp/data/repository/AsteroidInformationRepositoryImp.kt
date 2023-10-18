package com.example.spaceapp.data.repository

import com.example.spaceapp.data.storage.AsteroidInfoStorage
import com.example.spaceapp.data.storage.model.AsteroidInfoData
import com.example.spaceapp.data.storage.model.AsteroidInfoParamData
import com.example.spaceapp.domain.model.AsteroidInformation
import com.example.spaceapp.domain.model.AsteroidInformationParam
import com.example.spaceapp.domain.repository.AsteroidInformationRepository

class AsteroidInformationRepositoryImp(
    private val storage: AsteroidInfoStorage
) : AsteroidInformationRepository {
    override fun loadInformation(param: AsteroidInformationParam): AsteroidInformation {
        return mapToDomain(storage.load(mapToStorage(param)))
    }

    private fun mapToStorage(param: AsteroidInformationParam) : AsteroidInfoParamData {
        return AsteroidInfoParamData(param.startDate, param.endDate)
    }

    private fun mapToDomain(result: AsteroidInfoData) : AsteroidInformation {
        return AsteroidInformation(
            result.name,
            result.asteroidClass,
            result.solutionDate,
            result.dataArcSpan,
            result.planetaryEphem,
            result.sbEphem,
            result.conditionCode
        )
    }
}