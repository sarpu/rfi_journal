package com.sarpuner.journal

// Time suffix to remove from each episode.

const val SUFFIX: String = "20h00 GMT"

class Episode(url: String, title: String) {
    val title : String = title.removeSuffix(SUFFIX)
    val url : String = "$RFI_BASE$url"

    override fun toString() = "url:$url\ttitle:${title.removeSuffix(SUFFIX)}"
}