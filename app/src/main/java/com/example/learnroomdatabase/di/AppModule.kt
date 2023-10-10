package com.example.learnroomdatabase.di

import android.content.Context
import androidx.room.Room
import com.example.learnroomdatabase.data.local.ContactDao
import com.example.learnroomdatabase.data.local.ContactDatabase
import com.example.learnroomdatabase.data.repository.ContactRepositoryImpl
import com.example.learnroomdatabase.domain.repository.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideContactDatabase(@ApplicationContext context: Context): ContactDatabase {
        return Room.databaseBuilder(
            context,
            ContactDatabase::class.java,
            "contact_database"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideContactDao(contactDatabase: ContactDatabase): ContactDao {
        return contactDatabase.dao
    }

    @Provides
    @Singleton
    fun provideContactRepository(contactDao: ContactDao): ContactRepository {
        return ContactRepositoryImpl(contactDao)
    }
}