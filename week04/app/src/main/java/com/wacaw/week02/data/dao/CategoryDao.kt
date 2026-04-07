package com.wacaw.week02.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.wacaw.week02.data.entity.CategoryEntity

@Dao
interface CategoryDao {
    //새 카테고리를 삽입
    @Insert
    fun insertCategory(category: CategoryEntity)

    //카테고리 정보를 수정
    @Update
    fun updateCategory(category: CategoryEntity)

    //특정 카테고리를 삭제
    @Delete
    fun deleteCategory(category: CategoryEntity)

    @Query("SELECT * FROM CategoryTable")
    fun getAllCategories(): List<CategoryEntity>
}