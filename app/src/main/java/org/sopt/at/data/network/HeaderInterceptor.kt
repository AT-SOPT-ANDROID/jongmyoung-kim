package org.sopt.at.data.network

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.sopt.at.data.datasource.TokenDataStore
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val tokenDataStore: TokenDataStore,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addUserIdHeader()
            .build()
        return chain.proceed(newRequest)
    }

    private fun Request.Builder.addUserIdHeader(): Request.Builder = runBlocking {
        val userId = tokenDataStore.getUserId()

        addHeader("userId", "$userId")
    }
}
