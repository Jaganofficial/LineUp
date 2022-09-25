package com.lineup.app.di

import android.content.Context
import androidx.room.Room
import com.lineup.app.data.LineUpDatabase
import com.lineup.app.data.LineUpDatabaseDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//Provide module to Hilt
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideLineUpDAO(lineUpDatabase: LineUpDatabase):LineUpDatabaseDAO = lineUpDatabase.lineUpDAO()

    @Singleton
    @Provides
    fun provideLineUpDataBase( @ApplicationContext context:Context): LineUpDatabase =
        Room.databaseBuilder(context,LineUpDatabase::class.java,"lineup_database").fallbackToDestructiveMigration().build()
}