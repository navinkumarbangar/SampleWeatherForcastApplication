package com.example.navinbangar.sampleweatherapplication.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.navinbangar.sampleweatherapplication.api.Repository
import com.example.navinbangar.sampleweatherapplication.model.ListItem
import com.example.navinbangar.sampleweatherapplication.model.WeatherDetailHourly
import com.example.navinbangar.sampleweatherapplication.model.WeatherDetailsObj
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import retrofit2.Retrofit
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Navin Bangar on 11/19/2019.
 */

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: Repository = Repository()

    fun getHourlyWeatherForeCast(retrofit: Retrofit): MutableLiveData<WeatherDetailHourly?> {
        return mRepository.getHourlyForecastData(retrofit)
    }

    fun getSixteenDaysWeatherForeCast(retrofit: Retrofit): MutableLiveData<List<WeatherDetailsObj>> {
        return mRepository.getSixteenDaysForecastData(retrofit)
    }

    fun getHourlyForeCastHours(weatherDetailList: List<ListItem>?): List<String> {
        val hoursList = ArrayList<String>()
        weatherDetailList?.forEach {
            val hours = getHousFromDateString(it.dtTxt)
            hoursList.add(hours)
        }
        return hoursList
    }

    private fun getHousFromDateString(dateString: String): String {
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
            tempratureList.add(weatherObjListObj.temp.toString())
        }
        return tempratureList
    }

    fun getBarGraphData(weatherHoursList: List<String>, tempratureList: List<String>): BarData {
        val entries = ArrayList<BarEntry>()
        tempratureList.forEachIndexed { index, values ->
            entries.add(BarEntry(values.toFloat(), index))
        }
        val barDataSet = BarDataSet(entries, "Cells")
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        val data = BarData(weatherHoursList, barDataSet)
        return data
    }

}