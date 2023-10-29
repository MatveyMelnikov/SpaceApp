package com.example.spaceapp.data.storage

import android.content.Context
import com.example.spaceapp.data.storage.model.AsteroidInfoData
import com.example.spaceapp.data.storage.model.AsteroidInfoParamData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


const val PREFS_TAG = "SharedPrefs"

class LocalAsteroidInfoStorage(context: Context) : AsteroidInfoStorage  {
    private val sharedPreferences = context.getSharedPreferences(
        PREFS_TAG, Context.MODE_PRIVATE
    )
    private val editor = sharedPreferences.edit()
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)

    override suspend fun load(param: AsteroidInfoParamData): List<AsteroidInfoData>? {
        val savedData = sharedPreferences.getString(
            param.startDate.format(formatter), null
        ) ?: return null

        val gson = Gson()
        val type = object : TypeToken<List<AsteroidInfoData>>() {}.type
        return gson.fromJson(savedData, type)
    }

    override suspend fun save(date: LocalDate, data: List<AsteroidInfoData>) {
        val gson = Gson()
        val savingData = gson.toJson(data)

        editor.putString(date.format(formatter), savingData)
        editor.commit()
    }
}