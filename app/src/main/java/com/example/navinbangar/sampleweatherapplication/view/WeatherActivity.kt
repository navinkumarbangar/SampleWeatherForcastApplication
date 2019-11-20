package com.example.navinbangar.sampleweatherapplication.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.navinbangar.sampleweatherapplication.CustomApplication
import com.example.navinbangar.sampleweatherapplication.R
import com.example.navinbangar.sampleweatherapplication.di.factory.ViewModelFactory
import com.example.navinbangar.sampleweatherapplication.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class WeatherActivity : AppCompatActivity() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory
    protected lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpDagger()
        setUpViewModel()
        fetchHourlyForeCastDetaail()
        setUpHourlyForecastBtnListener()
        setUpCloseBtnClickListener()
        subscribeHourlyForeCastLiveData()
        setUpSixteenDaysForecastBtnListener()
    }

    private fun fetchHourlyForeCastDetaail() {
        weatherViewModel.getHourlyWeatherForeCastDetail()
    }

    private fun setUpCloseBtnClickListener() {
        btnCloseApp.setOnClickListener {
            finishAffinity()
        }
    }

    private fun subscribeHourlyForeCastLiveData() {
        weatherViewModel.getHourlyWeatherForeCastLiveData().observe(this, Observer { weatherDetailHourlyObj ->
            val weatherHoursList = weatherViewModel.getHourlyForeCastHours(weatherDetailHourlyObj?.list)
            val tempratureList = weatherViewModel.getHourlyForeCastTemprature(weatherDetailHourlyObj?.list)
            updateHourlyForeCast(weatherHoursList, tempratureList)
        })
    }

    private fun setUpDagger() {
        (application as CustomApplication).getNetworkComponentFn().inject(this)
    }

    private fun setUpViewModel() {
        weatherViewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
    }


    private fun setUpHourlyForecastBtnListener() {
        btnShowHourlyForcast.setOnClickListener {
            weatherViewModel.getHourlyWeatherForeCastDetail()
        }
    }

    private fun updateHourlyForeCast(weatherHoursList: List<String>, tempratureList: List<String>) {
        val barData = weatherViewModel.getBarGraphData(weatherHoursList, tempratureList)
        barChartForecast.data = barData
        barChartForecast.setDescription("Hourly Weather Forecast")
        barChartForecast.animateY(500)
    }

    private fun setUpSixteenDaysForecastBtnListener() {
        btnShowSixteenDaysForcast.setOnClickListener {
            weatherViewModel.getSixteenDaysWeatherForeCast().observe(this, Observer { data ->
                //TODO-As implemenation is almost same for this scenerio like in hourly forecast.Only need to change parameter in
                // request api so will take it up once hourly forecast will get finish
            })
        }
    }

}
