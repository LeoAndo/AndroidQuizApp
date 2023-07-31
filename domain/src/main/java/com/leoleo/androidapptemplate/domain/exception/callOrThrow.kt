package com.leoleo.androidapptemplate.domain.exception

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlin.jvm.Throws

@Throws(AppErrorType::class)
suspend fun <T> callOrThrow(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        try {
            apiCall.invoke()
        } catch (e: Throwable) {
            // TODO: アプリ固有のExceptionをthrowする
            throw AppErrorType.Unknown(e.message)
        }
    }
}