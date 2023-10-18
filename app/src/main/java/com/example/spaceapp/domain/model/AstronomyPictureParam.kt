package com.example.spaceapp.domain.model

import java.time.LocalDate

class AstronomyPictureParam(val date: LocalDate, val count: Int) : Param {
    override fun isCorrect(): Boolean {
        return count > 0
    }
}