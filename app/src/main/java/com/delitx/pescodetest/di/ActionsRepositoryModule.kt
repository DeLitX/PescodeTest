package com.delitx.pescodetest.di

import android.content.Context
import com.delitx.pescodetest.domain.local.SharedPreferencesSaver
import com.delitx.pescodetest.domain.repository.ActionsRepository
import com.delitx.pescodetest.domain.repository.ActionsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ActionsRepositoryModule {
    @Singleton
    @Provides
    fun provideActionsRepository(@ApplicationContext app: Context): ActionsRepository =
        ActionsRepositoryImpl(SharedPreferencesSaver(app))
}
