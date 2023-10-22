package com.example.spaceapp.presantation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spaceapp.data.repository.AstronomyPictureRepositoryImpl
import com.example.spaceapp.data.storage.NASAAstronomyPictureStorage
import com.example.spaceapp.domain.usecase.OpenAstronomyPictureUseCase

class AstronomyPictureViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val astronomyPictureRepository by lazy(LazyThreadSafetyMode.NONE) {
        AstronomyPictureRepositoryImpl(NASAAstronomyPictureStorage(context))
    }
    private val openAstronomyPictureUseCase by lazy(LazyThreadSafetyMode.NONE) {
        OpenAstronomyPictureUseCase(astronomyPictureRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AstronomyPictureViewModel(
            openAstronomyPictureUseCase
        ) as T
    }
}