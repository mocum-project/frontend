package com.example.myapplication

data class ResponseType<T>(
    val isSuccess: Boolean,
    val message:String,
    val result:T
)