package com.delitx.pescodetest.di

import com.delitx.pescodetest.domain.repository.ActionsRepository
import com.delitx.pescodetest.domain.repository.ActionsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ActionsRepositoryModule {
    @Singleton
    @Provides
    fun provideActionsRepository(): ActionsRepository = ActionsRepositoryImpl()
}
