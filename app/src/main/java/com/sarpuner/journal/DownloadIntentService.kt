package com.sarpuner.journal

import android.app.IntentService
import android.content.Intent
import android.util.Log
import java.io.File

private const val DOWNLOAD_INTENT_SERVICE_TAG = "Download Intent Service"


class DownloadIntentService : IntentService(DownloadIntentService::class.simpleName) {

    // TODO: Refactor this method to reduce the amount of dependencies.

    // This method downloads all the episodes on the main page, integrates text, and saves them.
    override fun onHandleIntent(downloadIntent: Intent) {
        val epiList: List<Episode> = parseMainPage()
        epiList.forEach {
            val fName = it.title.replace("/", "-").replace(" ", "")
            val downloadUrl = downloadData(it.url)
            val dir = this.filesDir
            Log.d(DOWNLOAD_INTENT_SERVICE_TAG, "filesDir: $dir")
            val fAudio = File(this.filesDir, "$fName.mp3")
            val fText = File(this.filesDir, "$fName.txt")
            Log.d(DOWNLOAD_INTENT_SERVICE_TAG, "The downloaded episodes are: ${it.title}")
            downloadAudio(downloadUrl, fAudio)
            downloadText(downloadUrl, fText)
            addTextToAudio(fText, fAudio)
        }
    }
}