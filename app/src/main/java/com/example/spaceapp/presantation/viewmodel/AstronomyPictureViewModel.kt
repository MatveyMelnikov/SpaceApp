package com.example.spaceapp.presantation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceapp.data.repository.AstronomyPictureRepositoryImpl
import com.example.spaceapp.data.storage.NASAAstronomyPictureStorage
import com.example.spaceapp.domain.model.AstronomyPicture
import com.example.spaceapp.domain.model.AstronomyPictureParam
import com.example.spaceapp.domain.usecase.OpenAstronomyPictureUseCase
import kotlinx.coroutines.launch

class AstronomyPictureViewModel : ViewModel() {
    val astronomyPicture = MutableLiveData<AstronomyPicture>()

    private val astronomyPictureRepository by lazy(LazyThreadSafetyMode.NONE) {
        AstronomyPictureRepositoryImpl(NASAAstronomyPictureStorage())
    }
    private val openAstronomyPictureUseCase by lazy(LazyThreadSafetyMode.NONE) {
        OpenAstronomyPictureUseCase(astronomyPictureRepository)
    }

    fun loadPicture(param: AstronomyPictureParam) {
        viewModelScope.launch {
            astronomyPicture.value = openAstronomyPictureUseCase.execute(param)
        }
    }
}