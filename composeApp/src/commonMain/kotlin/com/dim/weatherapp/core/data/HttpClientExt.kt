package com.dim.weatherapp.core.data


import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T> {
    val response = try {
        execute().body<T>()
    } catch (e: Exception) {
        return Result.failure(e)
    }

    return Result.success(response)
}


