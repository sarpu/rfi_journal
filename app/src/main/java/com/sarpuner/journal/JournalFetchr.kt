package com.sarpuner.journal

import org.jsoup.Jsoup
import org.jsoup.select.Elements

class JournalFetchr {
    fun parseHTML() {
        val programList: Elements = Jsoup . connect (RFI_WEBSITE).get().run {
            select("div.view-content h2 > a") }
        programList.forEach {
            println(it)
        }
    }
}