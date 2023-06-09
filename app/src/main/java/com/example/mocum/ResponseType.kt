package com.example.mocum

data class ResponseType<T>(
    val isSuccess: Boolean,
    val message:String,
    val result:T
)