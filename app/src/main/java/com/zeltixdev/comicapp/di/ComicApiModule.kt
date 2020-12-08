package com.zeltixdev.comicapp.di

import com.zeltixdev.comicapp.BuildConfig
import com.zeltixdev.comicapp.data.remote.ApiHelper
import com.zeltixdev.comicapp.data.remote.ApiHelperImpl
import com.zeltixdev.comicapp.data.remote.ApiService
import com.zeltixdev.comicapp.networking.HashUtils.toMD5
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://gateway.marvel.com/"

@Module
@InstallIn(ApplicationComponent::class)
object ComicApiModule {

    private const val ts = "1";
    private const val publicKey = BuildConfig.MARVEL_PUBLIC_KEY
    private const val privateKey = BuildConfig.MARVEL_PRIVATE_KEY

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val interceptor = Interceptor { chain: Interceptor.Chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url
                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("apikey", publicKey)
                    .addQueryParameter("hash", (ts + privateKey + publicKey).toMD5())
                    .build()
                val requestBuilder = originalRequest.newBuilder().url(newUrl)
            return@Interceptor chain.proceed(requestBuilder.build())
             }
        builder.addInterceptor(interceptor)
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}