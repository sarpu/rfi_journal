package com.sarpuner.journal

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import java.io.*
import java.net.URL
import java.net.URLConnection


private const val JOURNAL_FETCHR_TAG = "JournalFetchr"

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


// TODO: Refactor downloadAudio and downloadText methods, there is a lot of redundancy.

// TODO: Use download link to download and save the mp3 data to a CompleteEpisode instance

// TODO: Move to a new class where you can access files

// TODO: save the audio file as mp3!

fun downloadAudio(url: String, f: File) {
    val conn: URLConnection = URL(url).openConnection()
    val iStream: InputStream = conn.getInputStream()
    val oStream: OutputStream = FileOutputStream(f)
    var buffer: ByteArray = ByteArray(1024)
    var len = iStream.read(buffer)

    while (len > 0) {
        oStream.write(buffer, 0, len)
        len = iStream.read(buffer)
    }
    oStream.close()
    //val f: AudioFile = AudioFileIO.read(f)

}

// TODO: Download and transform the transcript as necessary.


// Download the text of each episode. The text() method gets all the combined text of the element
// and its children
fun downloadText(url: String, f: File) {

    // The line below makes sure the file is empty

    f.printWriter().use {
        print("")
    }
    val paragraphs = Jsoup.connect(url).get().run() {
        select("div.field-item > p")
    }
    for (p: Element in paragraphs) {
        f.appendText("${p.text()}\n")
    }
}


// POSSIBLE DELETION! This method will probably not be used.
private fun textNodesWithNewlines(e: Node): String {
    var temp: String = ""
    for (n: Node in e.childNodes()) {
        if (n is TextNode && !n.isBlank) {
            temp += "\n${n.text()}"
        }
        else temp += textNodesWithNewlines(n)
    }
    return temp
}
