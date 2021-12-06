package com.machmudow.weatherapp.common

sealed class ApiResult<out SuccessBody> {
    data class Success<SuccessBody>(val data: SuccessBody) : ApiResult<SuccessBody>()
    data class Failure(val error: Error = Error.Unknown) : ApiResult<Nothing>() {
        sealed interface Error {
            object NoConnection : Error
            object HttpException : Error
            object Unknown : Error
        }
    }
}

fun <SuccessBody> ApiResult<SuccessBody>.onSuccess(
    action: (SuccessBody) -> Unit
): ApiResult<SuccessBody> {
    if (this is ApiResult.Success) action(data)
    return this
}

fun <SuccessBody> ApiResult<SuccessBody>.onFailure(
    action: (ApiResult.Failure.Error) -> Unit
): ApiResult<SuccessBody> {
    if (this is ApiResult.Failure) action(error)
    return this
}

fun <SuccessBody> ApiResult<SuccessBody>.getOrNull() = if (this is ApiResult.Success) data else null
