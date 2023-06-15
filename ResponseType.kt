package com.example.materialregistration

data class ResponseType<T>(
    val isSuccess: Boolean,
    val message:String,
    val result:T
)