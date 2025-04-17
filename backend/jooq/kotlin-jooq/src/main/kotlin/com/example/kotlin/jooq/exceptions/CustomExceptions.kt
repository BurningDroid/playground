package com.example.kotlin.jooq.exceptions

sealed class BaseException(
    override val message: String,
    open val code: String
): RuntimeException(message)

class NotFoundException(
    override val message: String,
    override val code: String = "NOT_FOUND"
): BaseException(message, code)

class BadRequestException(
    override val message: String,
    override val code: String = "BAD_REQUEST"
): BaseException(message, code)

class UnauthorizedException(
    override val message: String,
    override val code: String = "UNAUTHORIZED"
): BaseException(message, code)

class ForbiddenException(
    override val message: String,
    override val code: String = "FORBIDDEN"
): BaseException(message, code)