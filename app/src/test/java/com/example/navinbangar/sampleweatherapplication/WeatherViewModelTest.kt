package com.example.navinbangar.sampleweatherapplication

import com.example.navinbangar.sampleweatherapplication.api.Repository
import com.example.navinbangar.sampleweatherapplication.viewmodel.WeatherViewModel
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


/**
 * Created by Navin Bangar on 11/21/2019.
 */

@RunWith(JUnit4::class)
class WeatherViewModelTest {
    private val repository = mock(Repository::class.java)
    private var weatherViewModelTest = WeatherViewModel(repository)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testNull() {
        assertThat(weatherViewModelTest.weatherRepo, notNullValue())
    }


}