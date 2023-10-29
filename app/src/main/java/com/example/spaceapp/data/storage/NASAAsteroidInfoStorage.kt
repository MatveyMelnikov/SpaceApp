package com.example.spaceapp.data.storage

import android.content.Context
import android.util.Log
import com.example.spaceapp.R
import com.example.spaceapp.data.storage.model.AsteroidInfoData
import com.example.spaceapp.data.storage.model.AsteroidInfoParamData
import com.example.spaceapp.data.storage.retrofit.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate

const val ASTEROID_TAG = "NASAAsteroidInfoStorage"

class NASAAsteroidInfoStorage(private val context: Context) : AsteroidInfoStorage {
    override suspend fun load(param: AsteroidInfoParamData): List<AsteroidInfoData>? {
        val response = try {
            RetrofitInstance.asteroidInfoApi.getAsteroidInfo(
                context.getString(R.string.api_key),
                param.startDate,
                param.endDate
            )
        } catch (e: IOException) {
            Log.e(ASTEROID_TAG, "IOException, you might not have internet connection")
            return null
        } catch (e: HttpException) {
            Log.e(ASTEROID_TAG, "HttpException, unexpected response")
            return null
        }

        if (!response.isSuccessful || response.body() == null)
            return null

        val result = mutableListOf<AsteroidInfoData>()

        for (it in response.body()!!.near_earth_objects) {
            for (asteroids in it.value) {
                result.add(
                    AsteroidInfoData(
                        asteroids.name,
                        asteroids.estimated_diameter.kilometers.estimated_diameter_max,
                        asteroids.is_potentially_hazardous_asteroid,
                        asteroids.close_approach_data[0].relative_velocity.kilometers_per_hour,
                        asteroids.close_approach_data[0].miss_distance.kilometers,
                        asteroids.close_approach_data[0].close_approach_date_full
                    )
                )
            }
        }

        return result.toList()
    }

    override suspend fun save(date: LocalDate, data: List<AsteroidInfoData>) {
        TODO("Not yet implemented")
    }
}