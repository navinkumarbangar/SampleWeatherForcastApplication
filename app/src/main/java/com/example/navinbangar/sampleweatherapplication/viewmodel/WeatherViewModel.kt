package com.example.navinbangar.sampleweatherapplication.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.navinbangar.sampleweatherapplication.api.Repo
import com.github.mikephil.charting.data.BarData
import retrofit2.Retrofit

/**
 * Created by Navin Bangar on 11/19/2019.
 */

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private var mRepository: Repo? = null

    init {
        mRepository = Repo()
    }

    fun currentData(retrofit: Retrofit): MutableLiveData<String> {
        mRepository = Repo()
        return mRepository!!.getCurrentData(retrofit)
    }


    fun forecastData(): MutableLiveData<BarData> {
        mRepository = Repo()
        return mRepository!!.getForecastData()!!
    }

}