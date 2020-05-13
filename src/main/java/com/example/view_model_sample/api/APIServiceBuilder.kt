package com.example.view_model_sample.api

import com.example.view_model_sample.AppConstants
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by januprasad on 12/05/19.
 */
object APIServiceBuilder {

    private val httpClient = OkHttpClient.Builder()

    //Taking Base URL from constants. check if we need multiple urls
    private val builder = Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        //                    .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
    private var retrofit: Retrofit? = null

    fun <S> createUserValidService(serviceClass: Class<S>, authToken: String?): S {

        //Clear Existing Interceptor
        httpClient.interceptors().clear()

        //Request Header Intercpetor
        val headerInterceptor = Interceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
            //Add Authorization if present
            if (authToken != null && authToken.isNotEmpty()) {
                requestBuilder.header("Authorization", " Bearer $authToken")
            }
            requestBuilder.method(original.method, original.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        //Log Interceptor to see the logs
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        //        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);


        //Add Interceptors
        httpClient.addInterceptor(headerInterceptor)
        httpClient.interceptors().add(interceptor)

        //Build Service
        val client = httpClient.build()
        retrofit = builder.client(client).build()
        return retrofit!!.create(serviceClass)
    }

    fun <S> createAuthService(serviceClass: Class<S>): S {

        //Taking Base URL from constants. check if we need multiple urls
        val builder = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            //                    .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

        //Clear Existing Interceptor
        httpClient.interceptors().clear()
        //Set timeouts
        httpClient.connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)

        //Log Interceptor to see the logs
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        //        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        httpClient.interceptors().add(interceptor)

        //Build Service
        val client = httpClient.build()
        return builder.client(client)
            .addConverterFactory(createGsonConverter())
            .build()
            .create(serviceClass)


    }

    fun <S> createPublicService(serviceClass: Class<S>): S {

        //Clear Existing Interceptor
        httpClient.interceptors().clear()

        //Log Interceptor to see the logs
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        //        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        httpClient.interceptors().add(interceptor)

        //Build Service
        val client = httpClient.build()
        retrofit = builder.client(client).addConverterFactory(
            createGsonConverter()
        ).build()

        return retrofit!!.create(serviceClass)
    }

    private fun createGsonConverter(): Converter.Factory {
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        return GsonConverterFactory.create(gson)
    }
}
