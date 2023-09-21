package com.example.kotlinwebserverdemo.response

data class Response<T: Any>(
    val message: String,
    val data: T? = null,
)
