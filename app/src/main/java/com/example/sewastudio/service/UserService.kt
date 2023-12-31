package com.example.sewastudio.service

import com.example.sewastudio.model.User
import retrofit2.Call
import retrofit2.http.GET

data class UserData (val username:String)
interface UserService {
    @GET("users")
    fun getUsers() : Call<List<User>>
}