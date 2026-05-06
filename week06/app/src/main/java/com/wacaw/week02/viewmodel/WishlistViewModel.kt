package com.wacaw.week02.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wacaw.week02.data.entity.ProductEntity
import com.wacaw.week02.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val repository : ProductRepository
): ViewModel(){
    private val _likedProducts = MutableLiveData<List<ProductEntity>>()
    val likedProducts: LiveData<List<ProductEntity>> = _likedProducts

    init{
        viewModelScope.launch (Dispatchers.IO) {
            repository.insertData()
            val products = repository.getLikedProducts()
            _likedProducts.postValue(products)
        }
    }
    fun toggleLike(id: Int, isLiked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLikeStatus(id, isLiked)
            // DB 업데이트 후 목록 재조회해서 LiveData 갱신
            val updated = repository.getLikedProducts()
            _likedProducts.postValue(updated)
        }
    }
}