package com.example.navinbangar.sampleweatherapplication.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.navinbangar.sampleweatherapplication.CustomApplication
import com.example.navinbangar.sampleweatherapplication.R
import com.example.navinbangar.sampleweatherapplication.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject

class WeatherActivity : AppCompatActivity() {
    protected lateinit var networkViewModel: WeatherViewModel
    @Inject
    lateinit var retrofit: Retrofit
    private var locationManager: LocationManager? = null
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpDagger()
        setUpViewModel()
        getLatitudeAndLongitude()
        setUpHourlyForecastBtnListener()
        setUpSixteenDaysForecastBtnListener()

    }

    private fun getLatitudeAndLongitude() {
        // Create persistent LocationManager reference
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                latitude = location.latitude
                longitude = location.longitude
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }
        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        } catch (ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available")
        }
    }

    private fun setUpDagger() {
        (application as CustomApplication).getNetworkComponentFn().inject(this)
    }

    private fun setUpViewModel() {
        networkViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
    }

    private fun setUpHourlyForecastBtnListener() {
        btnShowHourlyForcast.setOnClickListener {
            networkViewModel.getHourlyWeatherForeCast(retrofit, latitude, longitude).observe(this, Observer { t ->

            })
        }
    }

    private fun setUpSixteenDaysForecastBtnListener() {
        btnShowSixteenDaysForcast.setOnClickListener {
            networkViewModel.getSixteenDaysWeatherForeCast(retrofit, latitude, longitude).observe(this, Observer { data ->
                barChartSixteenDaysForecast.data = data // set the data and list of lables into chart
                val diff = barChartSixteenDaysForecast!!.data.yMax - barChartSixteenDaysForecast!!.data.yMin
                Log.d("DATAAA", diff.toString())
                val actual = diff.toString()
                var actualDiff = actual
                // Log.d("TTEXTACT", "TTEXT" + ttext + "ACTUALDIFF" + actualDiff)
                barChartSixteenDaysForecast!!.setDescription("Forecast Data")
                barChartSixteenDaysForecast!!.animateY(5000)

            })
        }


    }

}
