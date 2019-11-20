package com.example.navinbangar.sampleweatherapplication.api

import com.example.navinbangar.sampleweatherapplication.model.WeatherDetailHourly
import com.example.navinbangar.sampleweatherapplication.model.WeatherForeCast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Navin Bangar on 11/19/2019.
 */
interface WeatherServiceApiInterface {

    @GET("data/2.5/forecast/hourly?")
    fun getHourlyWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") appid: String): Call<WeatherDetailHourly>

    @GET("data/2.5/forecast?")
    fun getSixteenDaysForecastData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") appid: String): Call<WeatherForeCast>
}