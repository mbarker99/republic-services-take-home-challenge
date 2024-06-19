package com.barker.takehomechallenge.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class RoutesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .build()

        val request = chain.request().newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}