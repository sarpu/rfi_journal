package com.sarpuner.journal

import android.app.IntentService
import android.content.Intent

class DownloadIntentService : IntentService(DownloadIntentService::class.simpleName) {


    override fun onHandleIntent(downloadIntent: Intent) {
        val epiList: List<Episode> = parseMainPage()
    }
}