package com.example.navinbangar.sampleweatherapplication.api

import android.arch.lifecycle.MutableLiveData
import com.example.navinbangar.sampleweatherapplication.model.WeatherForeCast
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by Navin Bangar on 11/19/2019.
 */

class Repository {
    var hourlyRForeCastLiveData: MutableLiveData<BarData> = MutableLiveData()
    var sixteenDaysRForeCastLiveData: MutableLiveData<BarData> = MutableLiveData()

    //Get CURRENT WEATHER DATA
    fun getCurrentData(retrofit: Retrofit, latitude: Double, longitude: Double): MutableLiveData<BarData> {
        val service = retrofit.create(WeatherServiceApiInterface::class.java)
        var maxVal: MutableList<Float> = ArrayList()
        val call = service.getCurrentWeatherData(latitude.toString(), longitude.toString(), com.example.navinbangar.sampleweatherapplication.helper.Helper.ForecastAppId)
        call.enqueue(object : Callback<WeatherForeCast> {
            override fun onResponse(call: Call<WeatherForeCast>, response: Response<WeatherForeCast>) {
                if (response.code() == 200) {
                    val weatherForecast = response.body()!!
                    val tempDaily = listOf(weatherForecast.list)
                    tempDaily.forEach { t ->
                        t!!.forEach { f ->
                            // maxVal.add((f as Datum_).temperatureHigh)
                        }

                    }

                    // hourlyRForeCastLiveData.value = maxVal.toString()
                    sixteenDaysRForeCastLiveData.value = setBarChart(maxVal)


                }
            }

            override fun onFailure(call: Call<WeatherForeCast>, t: Throwable) {
                hourlyRForeCastLiveData.value = null
            }
        })

        return hourlyRForeCastLiveData
    }


    //Get weather forecast
    fun getSixteenDaysForecastData(retrofit: Retrofit, latitude: Double, longitude: Double): MutableLiveData<BarData> {
        val service = retrofit.create(WeatherServiceApiInterface::class.java)
        val call = service.getSixteenDaysForecastData(latitude.toString(), longitude.toString(), com.example.navinbangar.sampleweatherapplication.helper.Helper.ForecastAppId)
        var maxVal: MutableList<Float> = ArrayList()
        call.enqueue(object : Callback<WeatherForeCast> {
            override fun onResponse(call: Call<WeatherForeCast>, response: Response<WeatherForeCast>) {
                if (response.code() == 200) {
                    val weatherForecast = response.body()!!
                    val tempDaily = listOf(weatherForecast.list)
                    tempDaily.forEach { t ->
                        t!!.forEach { f ->
                            // maxVal.add((f as Datum_).temperatureHigh)
                        }

                    }

                    // hourlyRForeCastLiveData.value = maxVal.toString()
                    sixteenDaysRForeCastLiveData.value = setBarChart(maxVal)


                }
            }

            override fun onFailure(call: Call<WeatherForeCast>, t: Throwable) {
                sixteenDaysRForeCastLiveData.value = null
            }
        })
        return sixteenDaysRForeCastLiveData
    }


//PLOTTING BARCHART BASED ON FORECAST DATA

    private fun setBarChart(maxVal: MutableList<Float>): BarData {
        val entries = ArrayList<BarEntry>()
        maxVal.forEachIndexed { index, values ->
            entries.add(BarEntry(values, index))
        }

        val barDataSet = BarDataSet(entries, "Cells")

        val labels = ArrayList<String>()
        labels.add("18-Jan")
        labels.add("19-Jan")
        labels.add("20-Jan")
        labels.add("21-Jan")
        labels.add("22-Jan")
        labels.add("23-Jan")
        labels.add("24-Jan")
        labels.add("25-Jan")

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        val data = BarData(labels, barDataSet)

        return data
    }

}