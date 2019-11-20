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
    private var hourlyWeatherDetailsList = mutableListOf<WeatherDetailsObj>()
    private var sixteenWeatherDetailsList = mutableListOf<WeatherDetailsObj>()

    private val mRepository: Repository = Repository()
    private lateinit var weatherDate: String
    private lateinit var hourlyForeCastTemprature: String
    private lateinit var sixteenDaysForeCastTemprature: String
    private lateinit var hourlyWeatherDescription: String
    private lateinit var sixteenDaysWeatherDescription: String
    private lateinit var hourlyForecastHumidity: String
    private lateinit var sixteenDaysForecastHumidity: String
    private lateinit var hourlyForecastPressure: String

    fun getHourlyWeatherForeCast(retrofit: Retrofit): MutableLiveData<List<WeatherDetailsObj>> {
        return mRepository.getHourlyForecastData(retrofit)
    }

    fun getSixteenDaysWeatherForeCast(retrofit: Retrofit): MutableLiveData<List<WeatherDetailsObj>> {
        return mRepository.getSixteenDaysForecastData(retrofit)
    }


}