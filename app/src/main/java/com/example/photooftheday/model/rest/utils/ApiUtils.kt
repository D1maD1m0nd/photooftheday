package com.example.photooftheday.model.rest.utils

import com.example.photooftheday.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object ApiUtils {
    private const val TIMEOUT = 10L
    private const val BASE_URL_MAIN = "https://api.nasa.gov/"
    private const val BASE_PLANET_REPO = "planetary/"
    const val baseUrl = "$BASE_URL_MAIN$BASE_PLANET_REPO"

    fun getOkHTTPBuilderWithHeaders(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        httpClient.readTimeout(TIMEOUT, TimeUnit.SECONDS)
        httpClient.writeTimeout(TIMEOUT, TimeUnit.SECONDS)

        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .url(
                    original.url().newBuilder()
                        .addQueryParameter("api_key", BuildConfig.NASA_API_KEY)
                        .build()
                )
                .method(original.method(), original.body())
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

        return httpClient.build()
    }
}