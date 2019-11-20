package com.example.navinbangar.sampleweatherapplication.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.navinbangar.sampleweatherapplication.api.Repository
import com.example.navinbangar.sampleweatherapplication.model.ListItem
import com.example.navinbangar.sampleweatherapplication.model.WeatherDetailHourly
import com.example.navinbangar.sampleweatherapplication.model.WeatherForeCast
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


/**
 * Created by Navin Bangar on 11/19/2019.
 */

class WeatherViewModel @Inject constructor(val weatherRepo: Repository) : ViewModel() {
    private val hourlyWeatherForecastDetailLiveData = MutableLiveData<WeatherDetailHourly?>()

    fun getHourlyWeatherForeCastDetail(): MutableLiveData<WeatherDetailHourly?> {
        return weatherRepo.getHourlyForecastData(hourlyWeatherForecastDetailLiveData)
    }

    fun getHourlyWeatherForeCastLiveData(): MutableLiveData<WeatherDetailHourly?> {
        return hourlyWeatherForecastDetailLiveData
    }

    fun getSixteenDaysWeatherForeCast(): MutableLiveData<WeatherForeCast?> {
        return weatherRepo.getSixteenDaysForecastData()
    }

    fun getHourlyForeCastHours(weatherDetailList: List<ListItem>?): List<String> {
        val hoursList = ArrayList<String>()
        weatherDetailList?.forEach {
            val hours = getHoursFromDateString(it.dtTxt)
            hoursList.add(hours)
        }
        return hoursList.take(12)
    }

    private fun getHoursFromDateString(dateString: String): String {
        var hour = ""
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        try {
            val date = format.parse(dateString)
            val time = SimpleDateFormat("hh", Locale.US)
            hour = time.format(date)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return hour
    }

    fun getHourlyForeCastTemprature(weatherDetailList: List<ListItem>?): List<String> {
        val tempratureList = ArrayList<String>()
        weatherDetailList?.forEach {
            val weatherObjListObj = it.tempratureObj
            tempratureList.add(convertFahrenheitToCelcius(weatherObjListObj.temp).toString())
        }
        return tempratureList.take(12)
    }


    // Converts to celcius
    fun convertFahrenheitToCelcius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    //create bar data object
    fun getBarGraphData(weatherHoursList: List<String>, tempratureList: List<String>): BarData {
        val entries = ArrayList<BarEntry>()
        tempratureList.forEachIndexed { index, values ->
            entries.add(BarEntry(values.toFloat(), index))
        }
        val barDataSet = BarDataSet(entries, "Hourly Weather Forecast")
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        val data = BarData(weatherHoursList, barDataSet)
        return data
    }

}