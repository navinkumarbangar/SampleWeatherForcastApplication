package com.example.navinbangar.sampleweatherapplication.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.navinbangar.sampleweatherapplication.api.Repository
import com.github.mikephil.charting.data.BarData
import retrofit2.Retrofit

/**
 * Created by Navin Bangar on 11/19/2019.
 */

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: Repository = Repository()

    fun getHourlyWeatherForeCast(retrofit: Retrofit, latitude: Double, longitude: Double): MutableLiveData<String> {
        return mRepository.getCurrentData(retrofit, latitude, longitude)
    }

    fun getSixteenDaysWeatherForeCast(retrofit: Retrofit, latitude: Double, longitude: Double): MutableLiveData<BarData> {
        return mRepository.getSixteenDaysForecastData(retrofit, latitude, longitude)
    }
}