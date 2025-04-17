package com.example.kotlin.jooq.api

data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val error: ErrorResponse? = null
) {
    companion object {

        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(success = true, data = data)
        }

        fun <T> error(message: String, code: String): ApiResponse<T> {
            return ApiResponse(
                success = false,
                error = ErrorResponse(
                    message = message,
                    code = code
                )
            )
        }
    }
}

data class ErrorResponse(
    val message: String,
    val code: String
)