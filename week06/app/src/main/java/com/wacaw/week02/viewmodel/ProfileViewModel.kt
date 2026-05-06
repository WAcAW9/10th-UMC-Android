package com.wacaw.week02.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wacaw.week02.data.remote.response.UserData
import com.wacaw.week02.data.repository.ProductRepository
import com.wacaw.week02.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel(){
    private val _userList = MutableLiveData<List<UserData>>()
    val userList: LiveData<List<UserData>> = _userList

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    init {
        viewModelScope.launch {
            try {
                val response = repository.getUsers()
                if(response.isSuccessful){
                    val users = response.body()?.data ?: emptyList()
                    _userList.value = users
                }else{
                    _error.value = "${response.code()}"
                }
            }catch (e:Exception){
                _error.value = e.message ?: "Unknown Error"
            }
        }
    }
}