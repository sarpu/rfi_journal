package com.sarpuner.journal

import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

internal class JournalFetchrTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun parseMainPageTest() {
        val epiList: List<Episode> = parseMainPage()
        assertTrue(epiList is List<Episode>)

    }


}