package com.example.navinbangar.sampleweatherapplication.api

import android.arch.lifecycle.MutableLiveData
import com.example.navinbangar.sampleweatherapplication.model.WeatherDetailHourly
import com.example.navinbangar.sampleweatherapplication.model.WeatherForeCast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Navin Bangar on 11/19/2019.
 */

class Repository(val webservice: WeatherServiceApiInterface) {
    private var sixteenDaysForeCastLiveData: MutableLiveData<WeatherForeCast?> = MutableLiveData()

    ///Get weather forecast hourly
    fun getHourlyForecastData(hourlyrForeCastLiveData: MutableLiveData<WeatherDetailHourly?>): MutableLiveData<WeatherDetailHourly?> {
        val call = webservice.getHourlyWeatherData(lat, lon, com.example.navinbangar.sampleweatherapplication.helper.Helper.ForecastAppId)
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
    fun getSixteenDaysForecastData(): MutableLiveData<WeatherForeCast?> {
        val call = webservice.getSixteenDaysForecastData(lat, lon, com.example.navinbangar.sampleweatherapplication.helper.Helper.ForecastAppId)
        call.enqueue(object : Callback<WeatherForeCast> {
            override fun onResponse(call: Call<WeatherForeCast>, response: Response<WeatherForeCast>) {
                if (response.code() == success_code) {
                    val weatherForecastObj = response.body()
                    sixteenDaysForeCastLiveData.postValue(weatherForecastObj)
                }
            }
            override fun onFailure(call: Call<WeatherForeCast>, t: Throwable) {
                sixteenDaysForeCastLiveData.postValue(null)
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