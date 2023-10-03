package com.example.kotlinwebserverdemo.response

data class PagingResponse<T>(
    val page: Int = 0,
    val size: Int = 0,
    val totalRecord: Long = 0,
    val data: List<T>? = null,
)
