package com.example.sewastudio.service

import com.example.sewastudio.model.ApiResponse
import com.example.sewastudio.model.Studio
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

data class StudioData(
    @SerializedName("data")
    val data: StudioBody
)

data class UpdateStudioData(
    @SerializedName("data")
    val data: UpdateStudioBody
)

data class StudioBody(
    val name: String,
    @SerializedName("users_permissions_user")
    val ownerId: Int,
    @SerializedName("available")
    val available: Boolean
)

data class UpdateStudioBody(
    val name: String,
    val available: Boolean
)

interface StudioService {
    @POST("studios")
    fun insert(@Body body: StudioData): Call<ApiResponse<Studio>>
    @GET("studios")
    fun getstudios(
        @Query("populate") populate: String?
    ): Call<ApiResponse<List<Studio>>>

    @GET("studios")
    fun getall(
        @Query("filters[users_permissions_user]") usersPermissionsUser: String?,
        @Query("populate") populate: String?
    ): Call<ApiResponse<List<Studio>>>

    @PUT("studios/{id}")
    fun edit(@Path("id") id: Int, @Body body: UpdateStudioData): Call<ApiResponse<Studio>>

    @DELETE("studios/{id}")
    fun delete(@Path("id") id: Int): Call<ApiResponse<Studio>>
}

