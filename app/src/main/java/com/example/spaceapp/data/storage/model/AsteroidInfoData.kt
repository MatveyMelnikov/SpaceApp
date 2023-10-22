package com.example.spaceapp.data.storage.model

import java.time.LocalDateTime

class AsteroidInfoData(
    val name: String,
    val diameterMax: Double,
    val isPotentiallyHazardous: Boolean,
    val relativeVelocity: String,
    val missDistance: String,
    val closeApproachDate: String
) {
}