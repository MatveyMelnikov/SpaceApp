package com.example.spaceapp.domain.model

import java.time.LocalDateTime

class AsteroidInformation(
    val name: String,
    val diameterMax: Double,
    val isPotentiallyHazardous: Boolean,
    val relativeVelocity: Double,
    val missDistance: Double,
    val closeApproachDate: LocalDateTime
) {
}