package com.example.navinbangar.sampleweatherapplication.di.component

import com.example.navinbangar.sampleweatherapplication.di.ActivityModule
import com.example.navinbangar.sampleweatherapplication.di.FragmentModule
import com.example.navinbangar.sampleweatherapplication.di.NetworksModule
import com.example.navinbangar.sampleweatherapplication.di.ViewModelModule
import com.example.navinbangar.sampleweatherapplication.view.WeatherActivity
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Navin Bangar on 11/19/2019.
 */


@Singleton
@Component( modules = [NetworksModule::class,
    ViewModelModule::class, AndroidSupportInjectionModule::class,
    ActivityModule::class, FragmentModule::class])

interface NetworkComponent {
    fun inject(activity: WeatherActivity)
}
