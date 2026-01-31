package com.example.rickmorty.data.datasource

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory

fun createRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun createOkHttpClient(
    interceptor: HttpLoggingInterceptor
): OkHttpClient{
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(timeout = 15, unit = TimeUnit.SECONDS)
        .readTimeout(timeout = 30, unit = TimeUnit.SECONDS)
        .writeTimeout(timeout = 30, unit = TimeUnit.SECONDS)
        .callTimeout(timeout = 45, unit = TimeUnit.SECONDS)
        .build()


}
fun createHttpLoggingInterceptor(): HttpLoggingInterceptor{
    return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
}