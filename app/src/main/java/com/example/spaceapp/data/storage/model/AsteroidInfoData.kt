package com.example.spaceapp.data.storage.model

import java.time.LocalDateTime

class AsteroidInfoData(
    val name: String,
    val asteroidClass: String,
    val solutionDate: LocalDateTime,
    val dataArcSpan: Int,
    val planetaryEphem: String,
    val sbEphem: String,
    val conditionCode: Int
) {
}