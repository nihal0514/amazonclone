package com.example.amazonclone.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.amazonclone.model.category.CategoryListItem


@Database(entities = [CategoryListItem::class], version = 1)
abstract class CategoryDB : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
}