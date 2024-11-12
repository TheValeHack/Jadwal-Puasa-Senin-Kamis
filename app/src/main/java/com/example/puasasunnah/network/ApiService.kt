package com.example.puasasunnah.network

import com.example.puasasunnah.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/v1/fastings?type_id=1&Year=2024")
    fun getApiResponse(@Query("month") month: Int): Call<ApiResponse>
}