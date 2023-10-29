package com.example.spaceapp.presantation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spaceapp.data.repository.AsteroidInformationRepositoryImp
import com.example.spaceapp.data.storage.LocalAsteroidInfoStorage
import com.example.spaceapp.data.storage.NASAAsteroidInfoStorage
import com.example.spaceapp.domain.usecase.OpenAsteroidInformationUseCase

class AsteroidInfoViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val asteroidInformationRepository by lazy(LazyThreadSafetyMode.NONE) {
        AsteroidInformationRepositoryImp(
            NASAAsteroidInfoStorage(context),
            LocalAsteroidInfoStorage(context)
        )
    }
    private val openAsteroidInformationUseCase by lazy(LazyThreadSafetyMode.NONE) {
        OpenAsteroidInformationUseCase(asteroidInformationRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AsteroidInfoViewModel(
            openAsteroidInformationUseCase
        ) as T
    }
}