package com.example.navinbangar.sampleweatherapplication.model


import com.google.gson.annotations.SerializedName

data class Cord(@SerializedName("lon")
                val lon: Double = 0.0,
                @SerializedName("lat")
                val lat: Double = 0.0)


data class WindHourly(@SerializedName("deg")
                      val deg: Double = 0.0,
                      @SerializedName("speed")
                      val speed: Double = 0.0)


data class WeatherItem(@SerializedName("icon")
                       val icon: String = "",
                       @SerializedName("description")
                       val description: String = "",
                       @SerializedName("main")
                       val main: String = "",
                       @SerializedName("id")
                       val id: Int = 0)


data class CloudsDetail(@SerializedName("all")
                        val all: Int = 0)


data class WeatherDetailHourly(@SerializedName("city")
                               val city: CityDetailsHourly,
                               @SerializedName("cnt")
                               val cnt: Int = 0,
                               @SerializedName("cod")
                               val cod: String = "",
                               @SerializedName("message")
                               val message: Double = 0.0,
                               @SerializedName("list")
                               val list: List<ListItem>?)


data class CityDetailsHourly(@SerializedName("country")
                             val country: String = "",
                             @SerializedName("coord")
                             val coord: Cord,
                             @SerializedName("sunrise")
                             val sunrise: Int = 0,
                             @SerializedName("timezone")
                             val timezone: Int = 0,
                             @SerializedName("sunset")
                             val sunset: Int = 0,
                             @SerializedName("name")
                             val name: String = "",
                             @SerializedName("id")
                             val id: Int = 0,
                             @SerializedName("population")
                             val population: Int = 0)


data class ListItem(@SerializedName("dt")
                    val dt: Int = 0,
                    @SerializedName("dt_txt")
                    val dtTxt: String = "",
                    @SerializedName("weather")
                    val weather: List<WeatherItemDetails>?,
                    @SerializedName("main")
                    val tempratureObj: Temprature,
                    @SerializedName("clouds")
                    val clouds: CloudsDetail,
                    @SerializedName("sys")
                    val sys: SysDetailshourly,
                    @SerializedName("wind")
                    val wind: WindHourly)


data class SysDetailshourly(@SerializedName("pod")
                            val pod: String = "")


data class Temprature(@SerializedName("temp")
                      val temp: Double = 0.0,
                      @SerializedName("temp_min")
                      val tempMin: Double = 0.0,
                      @SerializedName("grnd_level")
                      val grndLevel: Double = 0.0,
                      @SerializedName("temp_kf")
                      val tempKf: Double = 0.0,
                      @SerializedName("humidity")
                      val humidity: Int = 0,
                      @SerializedName("pressure")
                      val pressure: Double = 0.0,
                      @SerializedName("sea_level")
                      val seaLevel: Double = 0.0,
                      @SerializedName("temp_max")
                      val tempMax: Double = 0.0)


