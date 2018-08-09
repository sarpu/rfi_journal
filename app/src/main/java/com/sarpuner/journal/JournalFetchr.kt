package com.sarpuner.journal

import org.jsoup.Jsoup
import org.jsoup.select.Selector.select

class JournalFetchr {
    fun parseHTML() {
        Jsoup.connect(R.string.rfi_website.toString()).get().run() {
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