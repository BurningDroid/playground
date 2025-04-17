package com.example.kotlin.jooq.exceptions

import com.example.kotlin.jooq.api.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException): ResponseEntity<ApiResponse<Unit>> {
        log.error("BaseException: ${e.message}", e)
        return ResponseEntity.status(getHttpStatus(e))
            .body(ApiResponse.error(message = e.message, code = e.code))
    }

    /**
     * RequestBody 검증 실패 시 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Unit>> {
        log.error("MethodArgumentNotValidException: $")
        val message = e.bindingResult.fieldErrors.joinToString(", ") { "${it.field}: ${it.defaultMessage}" }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error(message = message, code = "VALIDATION_ERROR"))
    }

    private fun getHttpStatus(e: BaseException): HttpStatus {
        return when (e) {
            is BadRequestException -> HttpStatus.BAD_REQUEST
            is ForbiddenException -> HttpStatus.FORBIDDEN
            is NotFoundException -> HttpStatus.NOT_FOUND
            is UnauthorizedException -> HttpStatus.UNAUTHORIZED
        }
    }
}