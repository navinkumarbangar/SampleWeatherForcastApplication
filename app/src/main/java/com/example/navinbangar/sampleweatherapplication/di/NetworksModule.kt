package com.example.navinbangar.sampleweatherapplication.di

import com.example.navinbangar.sampleweatherapplication.api.Repository
import com.example.navinbangar.sampleweatherapplication.api.WeatherServiceApiInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Navin Bangar on 11/19/2019.
 */

@Module
class NetworksModule constructor( var urlPath: String){
    @Singleton
    @Provides
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        return builder.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit {
        return Retrofit.Builder()
                .baseUrl(urlPath)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    @Singleton
    fun provideWeatherWebService(restAdapter: Retrofit): WeatherServiceApiInterface {
        return restAdapter.create(WeatherServiceApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(webservice: WeatherServiceApiInterface): Repository {
        return Repository(webservice)
    }
}