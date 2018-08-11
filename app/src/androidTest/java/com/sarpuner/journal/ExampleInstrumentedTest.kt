package com.sarpuner.journal

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getContext
import android.support.test.runner.AndroidJUnit4
import android.util.Log

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.io.File

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

const val INSTRUMENT_TAG = "InstrumentTest"

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.sarpuner.journal", appContext.packageName)
    }

    @Test
    fun downloadAndSaveAudio() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val url: String = "http://telechargement.rfi.fr/rfi/francais/audio/jff/201808/journal_francais_facile_20h00_-_20h10_tu_20180809.mp3"
        val name: String = "09-08-2018"
        //val dirs: File = File(appContext.filesDir, "tmp")
        //dirs.mkdirs()
        val f = File(appContext.filesDir, name)
        Log.d(INSTRUMENT_TAG, f.absolutePath)

        downloadAudio(url, f)

    }
}
