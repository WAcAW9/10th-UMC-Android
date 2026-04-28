package com.wacaw.week02.data.remote.service

import com.wacaw.week02.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/users")
    fun getUser(
        @Query("page") page: Int
    ): retrofit2.Call<UserResponse>
}