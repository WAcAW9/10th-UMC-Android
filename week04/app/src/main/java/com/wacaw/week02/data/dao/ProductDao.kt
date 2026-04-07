package com.wacaw.week02.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.wacaw.week02.data.entity.ProductEntity

@Dao
interface ProductDao {
    //새 상품을 삽입
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    //상품 정보를 수정
    @Update
    suspend fun updateProduct(product: ProductEntity)

    //특정 상품을 삭제
    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    //모든 상품 데이터
    @Query("SELECT * FROM ProductTable")
    suspend fun getAllProducts(): List<ProductEntity>

    //특정 카테고리의 상품만
    @Query("SELECT * FROM ProductTable WHERE category_id = :categoryId")
    suspend fun getProductsByCategory(categoryId: Int): List<ProductEntity>

    //좋아요 상품만
    @Query("SELECT * FROM ProductTable WHERE isLiked = 1")
    suspend fun getLikedProducts(): List<ProductEntity>

}