package com.sarpuner.journal

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.jaudiotagger.audio.AudioFile
import org.jaudiotagger.audio.AudioFileIO
import org.jsoup.Jsoup
import java.io.*
import java.net.URL
import java.net.URLConnection
import java.security.AccessController.getContext


/* The methods in this file will be called in an asynctask. Service may be an overkill, because we
do not wish it to run continuously. */

// TODO: Move the downloadData function call out of parseMainPage

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

// TODO: Find a better way to parse JSON, and extract the download link.

fun downloadData(episode: Episode) {
    println(episode.url)
    val jsonString: String = Jsoup.connect(episode.url).get().run{
        select("div[data-brainsonic]").attr("data-brainsonic")
    }
    val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    val tempObject: JsonObject = JsonParser().parse(jsonString).asJsonObject

    // TODO: This might be the ugliest piece of code ever written in the history of everdom. Refactor!

    println(gson.toJson(tempObject.getAsJsonObject("medias").getAsJsonObject("media").getAsJsonObject("media_sources").getAsJsonArray("media_source").get(0).asJsonObject.get("source")))
    //println(gson.toJson(tempObject))
}

// TODO: Use download link to download and save the mp3 data to a CompleteEpisode instance

// TODO: Move to a new class where you can access files

fun downloadAudio(url: String, f: File) {
    val conn: URLConnection = URL(url).openConnection()
    val iStream: InputStream = conn.getInputStream()
    val oStream: OutputStream = FileOutputStream(f)
    var buffer: ByteArray = ByteArray(1024)
    var len = iStream.read(buffer)

    while (len > 0) {
        println(len)
        oStream.write(buffer, 0, len)
        len = iStream.read(buffer)
    }
    oStream.close()
    //val f: AudioFile = AudioFileIO.read(f)

}

// TODO: Download and transform the transcript as necessary.


fun downloadText(url: String) {


}
