package com.example.spaceapp.presantation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceapp.data.repository.AsteroidInformationRepositoryImp
import com.example.spaceapp.domain.model.AsteroidInformation
import com.example.spaceapp.domain.model.AsteroidInformationParam
import com.example.spaceapp.domain.usecase.OpenAsteroidInformationUseCase

class AsteroidInfoViewModel : ViewModel() {
    val asteroidInfo = MutableLiveData<AsteroidInformation>()

    private val asteroidInformationRepository by lazy(LazyThreadSafetyMode.NONE) {
        AsteroidInformationRepositoryImp()
    }
    private val openAsteroidInformationUseCase by lazy(LazyThreadSafetyMode.NONE) {
        OpenAsteroidInformationUseCase(asteroidInformationRepository)
    }

    fun loadInfo(param: AsteroidInformationParam) {
        asteroidInfo.value = openAsteroidInformationUseCase.execute(param)
    }
}