package com.example.spaceapp.dependencyinjection

import com.example.spaceapp.domain.usecase.OpenAsteroidInformationUseCase
import com.example.spaceapp.domain.usecase.OpenAstronomyPictureUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<OpenAstronomyPictureUseCase> {
        OpenAstronomyPictureUseCase(repository = get())
    }

    factory<OpenAsteroidInformationUseCase> {
        OpenAsteroidInformationUseCase(repository = get())
    }
}