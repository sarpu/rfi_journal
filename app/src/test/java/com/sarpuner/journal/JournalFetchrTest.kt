package com.sarpuner.journal

import android.provider.SyncStateContract
import org.jsoup.Jsoup
import org.jsoup.select.Selector.select
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


import org.junit.jupiter.api.Assertions.*

internal class JournalFetchrTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun parseHTML() {
        Jsoup.connect().get().run() {
            select("div.rc").forEachIndexed { index, element ->
                val titleAnchor = element.select("h3 a")
                val title = titleAnchor.text()
                val url = titleAnchor.attr("href")
                //3. Dumping Search Index, Title and URL on the stdout.
                println("$index. $title ($url)")
            }

        }
    }
}