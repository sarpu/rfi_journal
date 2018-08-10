package com.sarpuner.journal

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.jsoup.select.Selector.select

class JournalFetchr {
    fun parseHTML() {
        val programList: Elements = Jsoup . connect (RFI_WEBSITE).get().run {
            select("div.view-content") }
        programList.select("div").forEach {
            print(it)
        }
    }
}