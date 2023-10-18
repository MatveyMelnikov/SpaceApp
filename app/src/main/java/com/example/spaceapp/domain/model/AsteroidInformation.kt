package com.example.spaceapp.domain.model

import java.time.LocalDateTime

class AsteroidInformation(
    val name: String,
    val asteroidClass: String,
    val solutionDate: LocalDateTime,
    val dataArcSpan: Int,
    val planetaryEphem: String,
    val sbEphem: String,
    val conditionCode: Int
) {
}