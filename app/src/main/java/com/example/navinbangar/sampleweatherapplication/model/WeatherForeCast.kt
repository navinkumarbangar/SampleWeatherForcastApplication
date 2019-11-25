package com.example.navinbangar.sampleweatherapplication.model


import com.google.gson.annotations.SerializedName

data class Coord(@SerializedName("lon")
                 val lon: Double = 0.0,
                 @SerializedName("lat")
                 val lat: Double = 0.0)


data class Wind(@SerializedName("deg")
                val deg: Double = 0.0,
                @SerializedName("speed")
                val speed: Double = 0.0)


data class WeatherItemDetails(@SerializedName("icon")
                       val icon: String = "",
                              @SerializedName("description")
                       val description: String = "",
                              @SerializedName("main")
                       val main: String = "",
                              @SerializedName("id")
                       val id: Int = 0)


data class Clouds(@SerializedName("all")
                  val all: Int = 0)


data class City(@SerializedName("country")
                val country: String = "",
                @SerializedName("coord")
                val coord: Coord,
                @SerializedName("name")
                val name: String = "",
                @SerializedName("id")
                val id: Int = 0)


data class WeatherDetailsObj(@SerializedName("dt")
                    val dt: Int = 0,
                             @SerializedName("dt_txt")
                    val dtTxt: String = "",
                             @SerializedName("weather")
                    val weather: List<WeatherItem>?,
                             @SerializedName("main")
                             val main: TempratureObj,
                             @SerializedName("clouds")
                    val clouds: Clouds,
                             @SerializedName("sys")
                    val sys: Sys,
                             @SerializedName("wind")
                    val wind: Wind)

data class Sys(@SerializedName("pod")
               val pod: String = "")


data class TempratureObj(@SerializedName("temp")
                val temp: Double = 0.0,
                         @SerializedName("temp_min")
                val tempMin: Double = 0.0,
                         @SerializedName("grnd_level")
                val grndLevel: Double = 0.0,
                         @SerializedName("temp_kf")
                         val tempKf: Double = 0.0,
                         @SerializedName("humidity")
                         val humidity: Double = 0.0,
                         @SerializedName("pressure")
                val pressure: Double = 0.0,
                         @SerializedName("sea_level")
                val seaLevel: Double = 0.0,
                         @SerializedName("temp_max")
                val tempMax: Double = 0.0)


data class WeatherForeCast(@SerializedName("city")
                           val city: City,
                           @SerializedName("cnt")
                           val cnt: Int = 0,
                           @SerializedName("cod")
                           val cod: String = "",
                           @SerializedName("message")
                           val message: Double = 0.0,
                           @SerializedName("list")
                           val list: List<WeatherDetailsObj>?)


