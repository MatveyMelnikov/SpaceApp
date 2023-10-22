package com.example.spaceapp.domain.model

import java.time.LocalDate

class AsteroidInformationParam(val startDate: LocalDate) : Param {
    override fun isCorrect(): Boolean {
        return true
    }
}