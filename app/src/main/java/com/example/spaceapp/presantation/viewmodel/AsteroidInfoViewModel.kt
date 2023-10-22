package com.example.spaceapp.presantation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceapp.domain.model.AsteroidInformation
import com.example.spaceapp.domain.model.AsteroidInformationParam
import com.example.spaceapp.domain.usecase.OpenAsteroidInformationUseCase
import kotlinx.coroutines.launch

class AsteroidInfoViewModel(
    private val openAsteroidInformationUseCase: OpenAsteroidInformationUseCase
) : ViewModel() {
    val asteroidInfo = MutableLiveData<List<AsteroidInformation>>()

    fun loadInfo(param: AsteroidInformationParam) {
        viewModelScope.launch {
            val data = openAsteroidInformationUseCase.execute(param) ?: return@launch
            asteroidInfo.value = data
        }
    }
}