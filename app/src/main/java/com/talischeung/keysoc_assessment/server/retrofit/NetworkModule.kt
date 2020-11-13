package com.talischeung.keysoc_assessment.server.retrofit

import com.talischeung.keysoc_assessment.BuildConfig
import com.talischeung.keysoc_assessment.server.Service
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@Suppress("unused")

object NetworkModule {
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideApi(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideService(): Retrofit {
        return Retrofit(
            retrofit2.Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.Host)
            .build())
    }
}