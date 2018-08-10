package com.sarpuner.journal

import org.json.JSONObject
import org.jsoup.Jsoup

/* The methods in this file will be called in an asynctask. Service may be an overkill, because we
do not wish it to run continuously. */

// Parse the main page of Journal en français facile to obtain a list of episodes.
fun parseMainPage() {
    var episode: Episode
    Jsoup.connect (RFI_WEBSITE).get().run {
        select(EPISODE_LINK_QUERY).forEach() {
            episode = Episode(it.attr("href"), it.text())
            //println(episode.toString())
            downloadData(episode)
        }
    }
}

fun downloadData(episode: Episode) {
    println(episode.url)
    val jsonString: String = Jsoup.connect(episode.url).get().run{
        select("div[data-brainsonic]").attr("data-brainsonic")
    }
    val tempObject: JSONObject = JSONObject(jsonString)
    println(tempObject)
}

fun downloadAudio() {

}

fun downloadText() {

}
