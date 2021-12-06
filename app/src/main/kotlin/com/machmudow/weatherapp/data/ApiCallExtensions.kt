package com.machmudow.weatherapp.data

import com.machmudow.weatherapp.common.ApiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

internal suspend fun <SuccessBody, Response> apiCall(
    request: suspend CoroutineScope.() -> Response,
    successMapper: (Response) -> SuccessBody
): ApiResult<SuccessBody> = withContext(Dispatchers.IO) {
    runCatching { request() }
        .map { response -> ApiResult.Success(successMapper(response)) }
        .onFailure(Throwable::printStackTrace)
        .getOrElse(Throwable::toApiResultFailure)
}

internal fun Throwable.toApiResultFailure(): ApiResult.Failure = when (this) {
    is UnknownHostException -> ApiResult.Failure(ApiResult.Failure.Error.NoConnection)
    is HttpException -> ApiResult.Failure(ApiResult.Failure.Error.HttpException)
    else -> ApiResult.Failure(ApiResult.Failure.Error.Unknown)
}
