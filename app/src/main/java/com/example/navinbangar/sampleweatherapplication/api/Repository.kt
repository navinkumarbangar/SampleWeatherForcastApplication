package com.example.navinbangar.sampleweatherapplication.api

import android.arch.lifecycle.MutableLiveData
import com.example.navinbangar.sampleweatherapplication.model.WeatherDetailHourly
import com.example.navinbangar.sampleweatherapplication.model.WeatherDetailsObj
import com.example.navinbangar.sampleweatherapplication.model.WeatherForeCast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by Navin Bangar on 11/19/2019.
 */

class Repository {

    private var hourlyrForeCastLiveData: MutableLiveData<WeatherDetailHourly?> = MutableLiveData()
    private var sixteenDaysForeCastLiveData: MutableLiveData<List<WeatherDetailsObj>> = MutableLiveData()

    ///Get weather forecast hourly
    fun getHourlyForecastData(retrofit: Retrofit): MutableLiveData<WeatherDetailHourly?> {
        val service = retrofit.create(WeatherServiceApiInterface::class.java)
        val call = service.getHourlyWeatherData(lat, lon, com.example.navinbangar.sampleweatherapplication.helper.Helper.ForecastAppId)
        call.enqueue(object : Callback<WeatherDetailHourly> {
            override fun onResponse(call: Call<WeatherDetailHourly>, response: Response<WeatherDetailHourly>) {
                if (response.code() == success_code) {
                    val weatherForecastObj = response.body()
                    hourlyrForeCastLiveData.value = weatherForecastObj
                }
            }

            override fun onFailure(call: Call<WeatherDetailHourly>, t: Throwable) {
                hourlyrForeCastLiveData.value = null
            }
        })

        return hourlyrForeCastLiveData
    }


    //Get weather forecast for 16 days
    fun getSixteenDaysForecastData(retrofit: Retrofit): MutableLiveData<List<WeatherDetailsObj>> {
        val service = retrofit.create(WeatherServiceApiInterface::class.java)
        val call = service.getSixteenDaysForecastData(lat, lon, com.example.navinbangar.sampleweatherapplication.helper.Helper.ForecastAppId)
        call.enqueue(object : Callback<WeatherForeCast> {
            override fun onResponse(call: Call<WeatherForeCast>, response: Response<WeatherForeCast>) {
                if (response.code() == success_code) {
                    val weatherForecastObj = response.body()
                    weatherForecastObj?.list?.let { sixteenDaysForeCastLiveData.value = it }
                }
            }
            override fun onFailure(call: Call<WeatherForeCast>, t: Throwable) {
                sixteenDaysForeCastLiveData.value = emptyList()
            }
        })
        return sixteenDaysForeCastLiveData
    }

    companion object {
        var lat = "9.96"
        var lon = "76.25"
        val success_code = 200
    }
}