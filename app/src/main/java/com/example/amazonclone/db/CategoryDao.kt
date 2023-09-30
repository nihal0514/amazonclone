package com.example.amazonclone.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.amazonclone.model.CategoryListItem


@Dao
interface CategoryDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(category:List<CategoryListItem>)

    @Query("SELECT * FROM CategoryListItem")
    fun getCategories(): List<CategoryListItem>
}