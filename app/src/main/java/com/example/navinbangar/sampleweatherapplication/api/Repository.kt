package com.example.navinbangar.sampleweatherapplication.api

import android.arch.lifecycle.MutableLiveData
import com.example.navinbangar.sampleweatherapplication.model.WeatherCurrentDetail
import com.example.navinbangar.sampleweatherapplication.model.WeatherDetailHourly
import com.example.navinbangar.sampleweatherapplication.model.WeatherForeCast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Navin Bangar on 11/19/2019.
 */

class Repository(val webservice: WeatherServiceApiInterface) {
    ///Get weather forecast hourly
    fun getCurrentWeatherData(currentWeatherLiveData: MutableLiveData<WeatherCurrentDetail?>, cityName: String, countryName: String): MutableLiveData<WeatherCurrentDetail?> {
        val call = webservice.getCurrentWeatherData(cityName + "," + countryName, com.example.navinbangar.sampleweatherapplication.helper.Helper.ForecastAppId)
        call.enqueue(object : Callback<WeatherCurrentDetail> {
            override fun onResponse(call: Call<WeatherCurrentDetail>, response: Response<WeatherCurrentDetail>) {
                if (response.code() == success_code) {
                    val weatherForecastObj = response.body()
                    currentWeatherLiveData.postValue(weatherForecastObj)
                }
            }

            override fun onFailure(call: Call<WeatherCurrentDetail>, t: Throwable) {
                currentWeatherLiveData.postValue(null)
            }
        })

        return currentWeatherLiveData
    }


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
    fun getSixteenDaysForecastData(sixteenDaysForeCastLiveData: MutableLiveData<WeatherForeCast?>, cityName: String, countryName: String): MutableLiveData<WeatherForeCast?> {
        val call = webservice.getSixteenDaysForecastData(cityName + "," + countryName, com.example.navinbangar.sampleweatherapplication.helper.Helper.ForecastAppId, mode, unit, cnt)
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
        const val unit = "metric"
        const val mode = "json"
        const val cnt = "16"
        const val success_code = 200
    }
}