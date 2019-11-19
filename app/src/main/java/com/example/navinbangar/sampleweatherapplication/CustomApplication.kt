package com.example.navinbangar.sampleweatherapplication

import android.app.Activity
import android.app.Application
import com.example.navinbangar.sampleweatherapplication.di.NetworksModule
import com.example.navinbangar.sampleweatherapplication.di.component.DaggerNetworkComponent
import com.example.navinbangar.sampleweatherapplication.di.component.NetworkComponent
import com.example.navinbangar.sampleweatherapplication.helper.Helper
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Navin Bangar on 11/19/2019.
 */


class CustomApplication : Application(), HasActivityInjector {
    lateinit var networkComponent: NetworkComponent

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        networkComponent = DaggerNetworkComponent.builder()
                .networksModule(NetworksModule(Helper.weatherUrl))
                .build()
    }

    fun getNetworkComponentFn(): NetworkComponent {
        return networkComponent
    }
}
