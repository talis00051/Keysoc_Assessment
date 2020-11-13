package com.talischeung.keysoc_assessment.server.retrofit

import retrofit2.Retrofit

abstract class RetrofitContainer(private val retrofit: Retrofit) {
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    fun getRetrofit(): Retrofit {
        return retrofit
    }
}