package com.sarpuner.journal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parseMainPage()
        val contxt = this.filesDir

    }

    // TODO: Add a scrolling list of RFI episodes.

    // TODO: Implement buttons to download specific episodes.

}
