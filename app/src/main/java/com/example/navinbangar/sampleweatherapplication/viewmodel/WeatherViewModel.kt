package com.example.navinbangar.sampleweatherapplication.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.navinbangar.sampleweatherapplication.api.Repository
import com.example.navinbangar.sampleweatherapplication.model.WeatherDetailsObj
import retrofit2.Retrofit

/**
 * Created by Navin Bangar on 11/19/2019.
 */

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private var hourlyrForeCastLiveData: MutableLiveData<List<WeatherDetailsObj>> = MutableLiveData()
    private var sixteenDaysForeCastLiveData: MutableLiveData<List<WeatherDetailsObj>> = MutableLiveData()
    private var hourlyWeatherDetailsList = mutableListOf<WeatherDetailsObj>()
    private var sixteenWeatherDetailsList = mutableListOf<WeatherDetailsObj>()

    private val mRepository: Repository = Repository()
    private lateinit var weatherDate: String
    private lateinit var temprature: String
    private lateinit var weatherDescription: String
    private lateinit var humidity: String
    private lateinit var pressure: String

    fun getHourlyWeatherForeCast(retrofit: Retrofit, latitude: Double, longitude: Double) {
        hourlyWeatherDetailsList = mRepository.getHourlyForecastData(retrofit, latitude, longitude).value?.toMutableList() ?: emptyList<WeatherDetailsObj>().toMutableList()
    }

    fun getSixteenDaysWeatherForeCast(retrofit: Retrofit, latitude: Double, longitude: Double) {
        sixteenWeatherDetailsList = mRepository.getHourlyForecastData(retrofit, latitude, longitude).value?.toMutableList() ?: emptyList<WeatherDetailsObj>().toMutableList()
    }

    fun getWeatherDate() = weatherDate
    fun getTemprature() = temprature
    fun getWeatherDescription() = weatherDescription
    fun getHumidity() = humidity
    fun getPressure() = pressure

    fun fetchWeatherDate() {

    }

}