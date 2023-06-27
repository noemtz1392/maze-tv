package mx.com.android.maze.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T : Any> safeApiCall(
    apiCall: suspend () -> Response<T>
): NetworkResult<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response = apiCall.invoke()
            return@withContext if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.code(), response.message())
            }


        } catch (ex: Exception) {
            return@withContext NetworkResult.Failure(ex)
        }
    }
}


sealed class NetworkResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : NetworkResult<T>()

    data class Error(val code: Int, val message: String) : NetworkResult<Nothing>()

    data class Failure(val exception: Exception) : NetworkResult<Nothing>()

    override fun toString(): String {
        val value = when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[code=$code, message=$message]"
            is Failure -> "Failure[exception=$exception]"
        }
        return value
    }
}
