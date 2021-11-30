package com.example.retrofit.models

data class PhotosByType(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
)