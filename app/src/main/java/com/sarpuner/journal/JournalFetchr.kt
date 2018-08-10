package com.sarpuner.journal

import org.jsoup.Jsoup

/* The methods in this file will be called in an asynctask. Service may be an overkill, because we
do not wish it to run continuously. */

// Parse the main page of Journal en français facile to obtain a list of episodes.
fun parseMainPage() {
    var episode: Episode
    Jsoup.connect (RFI_WEBSITE).get().run {
        select(EPISODE_LINK_QUERY).forEach() {
            episode = Episode(it.attr("href"), it.text())
            println(episode.toString())
        }
    }
}

fun downloadAudio() {

}
