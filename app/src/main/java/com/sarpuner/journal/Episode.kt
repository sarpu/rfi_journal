package com.sarpuner.journal

// Time suffix to remove from each episode.

const val SUFFIX: String = "20h00 GMT"

class Episode(val url: String, val title: String) {


    override fun toString() = "url:$url\ttitle:${title.removeSuffix(SUFFIX)}"
}