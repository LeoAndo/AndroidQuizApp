package com.leoleo.androidapptemplate.domain.exception

sealed class AppErrorType : Exception() {
    data class UnAuthorized(override val message: String?) : AppErrorType()
    data class Forbidden(override val message: String?) : AppErrorType()
    object Network : AppErrorType()
    data class UnprocessableEntity(override val message: String?) : AppErrorType()
    data class Unknown(override val message: String?) : AppErrorType()
    data class NotFound(override val message: String?) : AppErrorType()
    object Redirect : AppErrorType()
    object Server : AppErrorType()
}