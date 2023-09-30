package com.example.amazonclone.di

import android.content.Context
import androidx.room.Room
import com.example.amazonclone.db.CategoryDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCategoryDB(context:Context): CategoryDB{
        return Room.databaseBuilder(context, CategoryDB::class.java, "Category").build()
    }
}