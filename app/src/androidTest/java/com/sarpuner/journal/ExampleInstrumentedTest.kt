package com.sarpuner.journal

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.io.File


private const val INSTRUMENT_TAG = "InstrumentTest"

// TODO: There is a problem here with the class run!

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        assertEquals("com.sarpuner.journal", appContext.packageName)
    }

    @Test
    fun downloadAndSaveAudio() {
        val url = "http://telechargement.rfi.fr/rfi/francais/audio/jff/201808/journal_francais_facile_20h00_-_20h10_tu_20180809.mp3"
        val name = "09-08-2018.mp3"
        val f = File(appContext.filesDir, name)
        Log.d(INSTRUMENT_TAG, f.absolutePath)
        downloadAudio(url, f)
    }

    @Test
    fun downloadAndSaveTranscript() {
        val url = "https://savoirs.rfi.fr/fr/apprendre-enseigner/langue-francaise/journal-en-francais-facile-09082018-20h00-gmt"
        val name = "09-08-2018.txt"
        val f = File(appContext.filesDir, name)

        downloadText(url, f)
    }

    @Test
    fun addLyricsToAudio() {
        val audio: File = File(appContext.filesDir,"09-08-2018.mp3")
        val text: File = File(appContext.filesDir, "09-08-2018.txt")
        addTextToAudio(text, audio)
    }
}
