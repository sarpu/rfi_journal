package com.sarpuner.journal

import com.google.common.base.Charsets
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.jaudiotagger.audio.AudioFile
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.FieldKey
import org.jaudiotagger.tag.Tag
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.*
import java.net.URL


private const val JOURNAL_FETCHR_TAG = "JournalFetchr"

/* The methods in this file will be called in an asynctask. Service may be an overkill, because we
do not wish it to run continuously. Will probably add a button and scrolling list for episodes, or,
may incorporate something along the lines of webview.*/

// TODO: Move the downloadAudioURL function call out of parseMainPage

// Parse the main page of Journal en français facile to obtain a list of episodes.
fun parseMainPage() : MutableList<Episode> {
    val epiList: MutableList<Episode> = mutableListOf()
    Jsoup.connect (RFI_WEBSITE).get().run {
        select(EPISODE_LINK_QUERY).forEach {
            epiList.add(Episode(it.attr("href"), it.text()))
            //Log.d(JOURNAL_FETCHR_TAG, it.text())
        }
        return epiList
    }
}

// TODO: Find a better way to parse JSON, and extract the download link.

// This method returns the download links of each episode.

fun downloadAudioURL(episode_link: String): String {
    val jsonString: String = Jsoup.connect(episode_link).get().run{
        select("div[data-brainsonic]").attr("data-brainsonic")
    }
    val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    val tempObject: JsonObject = JsonParser().parse(jsonString).asJsonObject

    // TODO: This might be the ugliest piece of code ever written in the history of everdom. Refactor!

    // The line below extracts the download url from the JSON object, and returns it.
    return tempObject.getAsJsonObject("medias")
            .getAsJsonObject("media").getAsJsonObject("media_sources")
            .getAsJsonArray("media_source").get(0).asJsonObject.get("source").asString
}


fun downloadAudio(url: String, f: File) {
    val iStream: InputStream = URL(url).openStream()
    val oStream: OutputStream = f.outputStream()
    val buffer = ByteArray(1024)
    var len = iStream.read(buffer)

    while (len > 0) {
        oStream.write(buffer, 0, len)
        len = iStream.read(buffer)
    }
    oStream.close()
}

// TODO: Download and transform the transcript as necessary.


// Download the text of each episode. The text() method gets all the combined text of the element
// and its children, in order.
fun downloadText(url: String, f: File) {

    // The line below makes sure the file is empty
    f.printWriter().use {
        print("")
    }
    val paragraphs = Jsoup.connect(url).get().run {
        select("div.field-item > p")
    }
    for (p: Element in paragraphs) {
        f.appendText("${p.text()}\n")
    }
}

// The method below successfully adds unsynced lyrics! Works on samsung media player!!

// TODO: Try to sync the lyrics. Several open source libraries are available.

fun addTextToAudio(text: File, audio: File) {
    val audioFile: AudioFile = AudioFileIO.read(audio)
    val lyrics: String = com.google.common.io.Files.asCharSource(text, Charsets.UTF_8).read()
    val tag: Tag = audioFile.tag
    tag.setField(FieldKey.LYRICS, lyrics)
    audioFile.commit()
}

