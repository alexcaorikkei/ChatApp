package com.example.baseproject.di

import com.example.baseproject.data.repository.AuthRepositoryImpl
import com.example.baseproject.data.repository.RealTimeRepositoryImpl
import com.example.baseproject.domain.repository.AuthRepository
import com.example.baseproject.domain.repository.RealTimeRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class RealTimeDataModule {
    @Provides
    fun provideRealTimeRepository(): RealTimeRepository = RealTimeRepositoryImpl(
        database = FirebaseDatabase.getInstance()
    )
}
