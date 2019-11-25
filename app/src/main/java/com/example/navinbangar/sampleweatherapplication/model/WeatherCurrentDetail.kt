package com.example.navinbangar.sampleweatherapplication.model


import com.google.gson.annotations.SerializedName

data class Cordinates(@SerializedName("lon")
                      val lon: Double = 0.0,
                      @SerializedName("lat")
                      val lat: Double = 0.0)


data class WindDetails(@SerializedName("deg")
                       val deg: Int = 0,
                       @SerializedName("speed")
                       val speed: Double = 0.0)


data class CloudDetails(@SerializedName("all")
                        val all: Int = 0)


data class WeatherItems(@SerializedName("icon")
                        val icon: String = "",
                        @SerializedName("description")
                        val description: String = "",
                        @SerializedName("main")
                        val main: String = "",
                        @SerializedName("id")
                        val id: Int = 0)


data class Syst(@SerializedName("country")
                val country: String = "",
                @SerializedName("sunrise")
                val sunrise: Int = 0,
                @SerializedName("sunset")
                val sunset: Int = 0,
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("type")
                val type: Int = 0)


data class TempratureObject(@SerializedName("temp")
                            val temp: Double = 0.0,
                            @SerializedName("temp_min")
                            val tempMin: Double = 0.0,
                            @SerializedName("humidity")
                            val humidity: Int = 0,
                            @SerializedName("pressure")
                            val pressure: Int = 0,
                            @SerializedName("temp_max")
                            val tempMax: Double = 0.0)


data class WeatherCurrentDetail(@SerializedName("visibility")
                                val visibility: Int = 0,
                                @SerializedName("timezone")
                                val timezone: Int = 0,
                                @SerializedName("main")
                                val main: TempratureObject,
                                @SerializedName("clouds")
                                val clouds: CloudDetails,
                                @SerializedName("sys")
                                val sys: Syst,
                                @SerializedName("dt")
                                val dt: Int = 0,
                                @SerializedName("coord")
                                val coord: Cordinates,
                                @SerializedName("weather")
                                val weather: List<WeatherItems>?,
                                @SerializedName("name")
                                val name: String = "",
                                @SerializedName("cod")
                                val cod: Int = 0,
                                @SerializedName("id")
                                val id: Int = 0,
                                @SerializedName("base")
                                val base: String = "",
                                @SerializedName("wind")
                                val wind: WindDetails)


