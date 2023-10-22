package com.example.spaceapp.data.repository

import com.example.spaceapp.data.storage.AstronomyPictureStorage
import com.example.spaceapp.data.storage.model.AstronomyPictureData
import com.example.spaceapp.data.storage.model.AstronomyPictureDataParam
import com.example.spaceapp.domain.model.AstronomyPicture
import com.example.spaceapp.domain.model.AstronomyPictureParam
import com.example.spaceapp.domain.repository.AstronomyPictureRepository


class AstronomyPictureRepositoryImpl(
    private val storage: AstronomyPictureStorage
) : AstronomyPictureRepository {
    override suspend fun loadPucture(param: AstronomyPictureParam) : AstronomyPicture? {
        return mapToDomain(storage.load(mapToStorage(param)))
    }

    private fun mapToStorage(param: AstronomyPictureParam) : AstronomyPictureDataParam {
        return AstronomyPictureDataParam(param.date, param.count)
    }

    private fun mapToDomain(result: AstronomyPictureData?) : AstronomyPicture? {
        return if (result == null)
            null
        else
            AstronomyPicture(result.drawable, result.explanation, result.title)
    }
}