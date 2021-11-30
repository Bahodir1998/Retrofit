package com.example.retrofit.retrofit

object Common {

    var BASE_URL = "https://api.unsplash.com/"

    val retrofitServices: RetrofitServices
    get() = RetrofitClient.getRetrofit(BASE_URL).create(RetrofitServices::class.java)

}