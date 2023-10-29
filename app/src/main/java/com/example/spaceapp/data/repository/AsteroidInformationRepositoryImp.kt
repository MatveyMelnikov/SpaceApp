package com.example.spaceapp.data.repository

import com.example.spaceapp.data.storage.AsteroidInfoStorage
import com.example.spaceapp.data.storage.model.AsteroidInfoData
import com.example.spaceapp.data.storage.model.AsteroidInfoParamData
import com.example.spaceapp.domain.model.AsteroidInformation
import com.example.spaceapp.domain.model.AsteroidInformationParam
import com.example.spaceapp.domain.repository.AsteroidInformationRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class AsteroidInformationRepositoryImp(
    private val externalStorage: AsteroidInfoStorage,
    private val localStorage: AsteroidInfoStorage,
) : AsteroidInformationRepository {
    override suspend fun loadInformation(
        param: AsteroidInformationParam
    ) : List<AsteroidInformation>? {
        val internalParams = mapToStorage(param)
        val result = externalStorage.load(internalParams) ?:
            localStorage.load(internalParams)

        if (result != null)
            localStorage.save(param.startDate, result)

        return mapToDomain(result)
    }

    private fun mapToStorage(param: AsteroidInformationParam) : AsteroidInfoParamData {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        return AsteroidInfoParamData(
            param.startDate.format(formatter),
            param.startDate.plusDays(1).format(formatter)
        )
    }

    private fun mapToDomain(result: List<AsteroidInfoData>?) : List<AsteroidInformation>? {
        if (result == null)
            return null

        val mappedResult = mutableListOf<AsteroidInformation>()
        for (it in result) {
            // "2015-Sep-10 08:30"
            val formatter = DateTimeFormatter.ofPattern(
                "uuuu-LLL-dd HH:mm", Locale.ENGLISH
            )

            mappedResult.add(
                AsteroidInformation(
                    it.name,
                    it.diameterMax,
                    it.isPotentiallyHazardous,
                    it.relativeVelocity.toDouble(),
                    it.missDistance.toDouble(),
                    LocalDateTime.parse(it.closeApproachDate, formatter)
                )
            )
        }
        return mappedResult.toList()
    }
}