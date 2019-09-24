package net.furkanakdemir.branchsample.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(QUERY_CLIENT_ID, QUERY_CLIENT_ID_VALUE)
            .addQueryParameter(QUERY_CLIENT_SECRET, QUERY_CLIENT_SECRET_VALUE)
            .addQueryParameter(QUERY_VERSION, QUERY_VERSION_VALUE)
            .build()

        val newRequest: Request = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        private const val QUERY_CLIENT_ID = "client_id"
        private const val QUERY_CLIENT_SECRET = "client_secret"
        private const val QUERY_VERSION = "v"

        private const val QUERY_CLIENT_ID_VALUE =
            "BHNYCQM5AZD1Z1P5QHUKZYPJ1H414ARR5M30OSKCGUUA1RE2"
        private const val QUERY_CLIENT_SECRET_VALUE =
            "INQN5BAQKU2LWWULMCURX5CA0WY40GD3V1ISQ4ERVOFZ5YFV"
        private const val QUERY_VERSION_VALUE = "20190924"
    }
}
