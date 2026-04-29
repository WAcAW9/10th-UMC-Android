package com.wacaw.week02.data.remote.service

import com.wacaw.week02.data.remote.response.UserResponse
import retrofit2.http.GET

interface UserService {
    @GET("api/users")
    fun getUser(): retrofit2.Call<UserResponse>
}