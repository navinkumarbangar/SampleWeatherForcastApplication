package com.example.navinbangar.sampleweatherapplication.api

import android.arch.lifecycle.MutableLiveData
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
    private var hourlyrForeCastLiveData: MutableLiveData<List<WeatherDetailsObj>> = MutableLiveData()
    private var sixteenDaysForeCastLiveData: MutableLiveData<List<WeatherDetailsObj>> = MutableLiveData()

    ///Get weather forecast hourly
    fun getHourlyForecastData(retrofit: Retrofit, latitude: Double, longitude: Double): MutableLiveData<List<WeatherDetailsObj>> {
        val service = retrofit.create(WeatherServiceApiInterface::class.java)
        val call = service.getCurrentWeatherData(latitude.toString(), longitude.toString(), com.example.navinbangar.sampleweatherapplication.helper.Helper.ForecastAppId)
        call.enqueue(object : Callback<WeatherForeCast> {
            override fun onResponse(call: Call<WeatherForeCast>, response: Response<WeatherForeCast>) {
                if (response.code() == 200) {
                    val weatherForecastObj = response.body()
                    val weatherForecastObjectList = ArrayList<WeatherDetailsObj>()
                    weatherForecastObj?.list?.let { weatherForecastObjectList.addAll(it) }
                    hourlyrForeCastLiveData.value = weatherForecastObjectList
                }

            }
            override fun onFailure(call: Call<WeatherForeCast>, t: Throwable) {
                hourlyrForeCastLiveData.value = null
            }
        })

        return hourlyrForeCastLiveData
    }


    //Get weather forecast for 16 days
    fun getSixteenDaysForecastData(retrofit: Retrofit, latitude: Double, longitude: Double): MutableLiveData<List<WeatherDetailsObj>> {
        val service = retrofit.create(WeatherServiceApiInterface::class.java)
        val call = service.getSixteenDaysForecastData(latitude.toString(), longitude.toString(), com.example.navinbangar.sampleweatherapplication.helper.Helper.ForecastAppId)
        call.enqueue(object : Callback<WeatherForeCast> {
            override fun onResponse(call: Call<WeatherForeCast>, response: Response<WeatherForeCast>) {
                if (response.code() == 200) {
                    val weatherForecastObj = response.body()
                    val weatherForecastObjectList = ArrayList<WeatherDetailsObj>()
                    weatherForecastObj?.list?.let { weatherForecastObjectList.addAll(it) }
                    sixteenDaysForeCastLiveData.value = weatherForecastObjectList
                }
            }
            override fun onFailure(call: Call<WeatherForeCast>, t: Throwable) {
                sixteenDaysForeCastLiveData.value = null
            }
        })
        return sixteenDaysForeCastLiveData
    }
}