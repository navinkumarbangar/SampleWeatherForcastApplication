package com.example.navinbangar.sampleweatherapplication.di

import com.example.navinbangar.sampleweatherapplication.view.WeatherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Navin Bangar on 11/19/2019.
 */

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeWeatherActivity(): WeatherActivity
}