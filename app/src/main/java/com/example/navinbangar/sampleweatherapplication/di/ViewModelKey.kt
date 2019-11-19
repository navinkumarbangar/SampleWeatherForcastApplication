package com.example.navinbangar.sampleweatherapplication.di

import dagger.MapKey
import android.arch.lifecycle.ViewModel
import kotlin.reflect.KClass
/**
 * Created by Navin Bangar on 11/19/2019.
 */

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)