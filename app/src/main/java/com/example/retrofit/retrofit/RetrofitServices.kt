package com.example.retrofit.retrofit

import com.example.retrofit.models.PhotosByType
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("search/photos")
    fun getPhotos(
        @Query("query") query: String,
        @Query("client_id") key: String,
        @Query("per_page") page: Int
    ): Call<PhotosByType>
}