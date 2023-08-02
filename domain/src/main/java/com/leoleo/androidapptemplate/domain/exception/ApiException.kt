package com.leoleo.androidapptemplate.domain.exception

sealed class ApiException : AppException() {
    data class UnAuthorized(override val message: String?) : ApiException()
    data class Forbidden(override val message: String?) : ApiException()
    object Network : ApiException()
    data class UnprocessableEntity(override val message: String?) : ApiException()
    data class Unknown(override val message: String?) : ApiException()
    data class NotFound(override val message: String?) : ApiException()
    object Redirect : ApiException()
    object Server : ApiException()
}