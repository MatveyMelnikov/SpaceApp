package com.example.spaceapp.presantation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceapp.data.repository.AstronomyPictureRepositoryImpl
import com.example.spaceapp.data.storage.NASAAstronomyPictureStorage
import com.example.spaceapp.domain.model.AstronomyPicture
import com.example.spaceapp.domain.model.AstronomyPictureParam
import com.example.spaceapp.domain.usecase.OpenAstronomyPictureUseCase
import kotlinx.coroutines.launch

class AstronomyPictureViewModel(
    private val openAstronomyPictureUseCase: OpenAstronomyPictureUseCase
) : ViewModel() {
    val astronomyPicture = MutableLiveData<AstronomyPicture>()

    fun loadPicture(param: AstronomyPictureParam) {
        viewModelScope.launch {
            val data = openAstronomyPictureUseCase.execute(param) ?: return@launch
            astronomyPicture.value = data
        }
    }
}