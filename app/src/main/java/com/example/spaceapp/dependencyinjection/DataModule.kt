package com.example.spaceapp.dependencyinjection

import com.example.spaceapp.data.repository.AsteroidInformationRepositoryImp
import com.example.spaceapp.data.repository.AstronomyPictureRepositoryImpl
import com.example.spaceapp.data.storage.AsteroidInfoStorage
import com.example.spaceapp.data.storage.AstronomyPictureStorage
import com.example.spaceapp.data.storage.LocalAsteroidInfoStorage
import com.example.spaceapp.data.storage.NASAAsteroidInfoStorage
import com.example.spaceapp.data.storage.NASAAstronomyPictureStorage
import com.example.spaceapp.domain.repository.AsteroidInformationRepository
import com.example.spaceapp.domain.repository.AstronomyPictureRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    // Astronomy picture
    single<AstronomyPictureStorage> {
        NASAAstronomyPictureStorage(context = get())
    }

    single<AstronomyPictureRepository> {
        AstronomyPictureRepositoryImpl(storage = get())
    }

    // Asteroid info
    single<AsteroidInfoStorage>(named("NASA_STORAGE")) {
        NASAAsteroidInfoStorage(context = get())
    }

    single<AsteroidInfoStorage>(named("LOCAL_STORAGE")) {
        LocalAsteroidInfoStorage(context = get())
    }

    // Two parameters of the same interface type.
    // To determine which implementation to insert, we create names
    single<AsteroidInformationRepository> {
        AsteroidInformationRepositoryImp(
            externalStorage = get(named("NASA_STORAGE")),
            localStorage = get(named("LOCAL_STORAGE"))
        )
    }
}