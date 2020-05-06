package com.example.navinbangar.sampleweatherapplication

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.microsoft.appcenter.espresso.Factory
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    var reportHelper = Factory.getReportHelper()
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.navinbangar.sampleweatherapplication", appContext.packageName)
    }

    @After
    fun TearDown() {
        reportHelper.label("Stopping App")
    }
}
