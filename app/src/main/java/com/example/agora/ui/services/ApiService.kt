package com.example.agora.ui.services

import com.example.agora.ui.data.WeatherData
import com.squareup.moshi.Moshi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_URL = "https://api.openweathermap.org/data/2.5/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
    API_URL).addCallAdapterFactory(CoroutineCallAdapterFactory()).build()

interface ApiService {
    @GET("weather")
    fun getProperties(@Query("lat")lat:String,@Query("lon")lon:String,@Query("appid")key:String, @Query("units")metric:String):
            Deferred<WeatherData>
}

object AgoraApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java) }
}