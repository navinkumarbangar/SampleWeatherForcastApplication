package com.example.navinbangar.sampleweatherapplication.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.navinbangar.sampleweatherapplication.di.factory.ViewModelFactory
import com.example.navinbangar.sampleweatherapplication.viewmodel.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Navin Bangar on 11/19/2019.
 */

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    protected abstract fun weatherViewModel(moviesListViewModel: WeatherViewModel): ViewModel
}