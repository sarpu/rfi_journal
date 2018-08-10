package com.sarpuner.journal

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.jsoup.select.Selector.select

class JournalFetchr {
    fun parseHTML() {
        var episode: Episode
        Jsoup.connect (RFI_WEBSITE).get().run {
            select("div.view-content h2 > a").forEach() {
                episode = Episode(it.attr("href"), it.text())
            }
        }
    }
}