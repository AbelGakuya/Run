package com.amg.run.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.amg.run.db.RunningDatabase
import com.amg.run.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.amg.run.other.Constants.KEY_NAME
import com.amg.run.other.Constants.KEY_WEIGHT
import com.amg.run.other.Constants.RUNNING_DATABASE_NAME
import com.amg.run.other.Constants.SHARED_PREFERENCES_NAME
//import com.example.run.other.Constants.KEY_FIRST_TIME_TOGGLE
//import com.example.run.other.Constants.KEY_NAME
//import com.example.run.other.Constants.KEY_WEIGHT
//import com.example.run.other.Constants.RUNNING_DATABASE_NAME
//import com.example.run.other.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    )
            = Room.databaseBuilder(
        app,
        RunningDatabase::class.java,
        RUNNING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db:RunningDatabase) = db.getDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPref: SharedPreferences) = sharedPref.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPref: SharedPreferences) = sharedPref.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences) =
        sharedPref.getBoolean(KEY_FIRST_TIME_TOGGLE, true)

}