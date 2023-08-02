package com.leoleo.androidapptemplate.domain.exception

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlin.jvm.Throws

@Throws(ApiException::class)
suspend fun <T> callApiOrThrow(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        try {
            apiCall.invoke()
        } catch (e: Throwable) {
            // TODO: アプリ固有のExceptionをthrowする
            throw ApiException.Unknown(e.message)
        }
    }
}