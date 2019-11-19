package com.example.navinbangar.sampleweatherapplication.di.component

import android.app.Application
import com.example.navinbangar.sampleweatherapplication.CustomApplication
import com.example.navinbangar.sampleweatherapplication.di.ActivityModule
import com.example.navinbangar.sampleweatherapplication.di.FragmentModule
import com.example.navinbangar.sampleweatherapplication.di.NetworksModule
import com.example.navinbangar.sampleweatherapplication.di.ViewModelModule
import dagger.BindsInstance
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
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        @BindsInstance
        fun networkModule(networksModule: NetworksModule): Builder
        fun build(): NetworkComponent
    }

    fun inject(customApplication: CustomApplication)
}
