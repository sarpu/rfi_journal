package com.sarpuner.journal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // TODO: Add a scrolling list of RFI episodes.

    // TODO: Implement buttons to download specific episodes.

    fun download(view: View) {
        val intent = Intent(this, DownloadIntentService::class.java)
        startService(intent)
    }
}
