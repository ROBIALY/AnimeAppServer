package com.robertconstantin.common

data class ApiResponse<T>(
    val successful: Boolean,
    //message for error. Could be nullable
    val message: String? = null,
    //data to be sent to client. Could be null fi there is an error
    val data: T? = null
)
