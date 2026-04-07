package com.wacaw.week02.data.repository

import com.wacaw.week02.R
import com.wacaw.week02.data.dao.CategoryDao
import com.wacaw.week02.data.dao.ProductDao
import com.wacaw.week02.data.entity.ProductEntity

class ProductRepository (private val productDao: ProductDao,private val categoryDao: CategoryDao){
    suspend fun insertData(){
        productDao.insertProduct(ProductEntity(id = 1,name = "Air Jordan", content = "content1", image_resource = R.drawable.img_sample_product_1, price = 1, isLiked = false, category_id = 1))
        productDao.insertProduct(ProductEntity(id = 2,name = "Air Jordan2", content = "content2", image_resource = R.drawable.img_sample_product_2, price = 1, isLiked = true, category_id = 1))
    }

    suspend fun getAllProducts(): List<ProductEntity> = productDao.getAllProducts()

    suspend fun getLikedProducts(): List<ProductEntity> = productDao.getLikedProducts()

    suspend fun getProductsByCategory(categoryId: Int): List<ProductEntity> =
        productDao.getProductsByCategory(categoryId)
}