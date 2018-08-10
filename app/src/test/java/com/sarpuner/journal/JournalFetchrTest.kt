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
        val fetchr = JournalFetchr()
        fetchr.parseHTML()
    }
}