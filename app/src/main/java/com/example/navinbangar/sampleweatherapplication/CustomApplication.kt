package com.example.navinbangar.sampleweatherapplication

import android.app.Activity
import android.app.Application
import com.example.navinbangar.sampleweatherapplication.di.DaggerNetworkComponent
import com.example.navinbangar.sampleweatherapplication.di.NetworksModule
import com.example.navinbangar.sampleweatherapplication.helper.Helper
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Navin Bangar on 11/19/2019.
 */


class CustomApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerNetworkComponent.builder()
                .application(this)
                .networkModule(NetworksModule(Helper.weatherUrl))
                .build()
                .inject(this)
    }

}
