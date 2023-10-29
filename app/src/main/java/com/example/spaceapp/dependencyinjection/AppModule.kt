package com.example.spaceapp.dependencyinjection

import com.example.spaceapp.presantation.viewmodel.AsteroidInfoViewModel
import com.example.spaceapp.presantation.viewmodel.AstronomyPictureViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        AsteroidInfoViewModel(openAsteroidInformationUseCase = get())
    }

    viewModel {
        AstronomyPictureViewModel(openAstronomyPictureUseCase = get())
    }
}