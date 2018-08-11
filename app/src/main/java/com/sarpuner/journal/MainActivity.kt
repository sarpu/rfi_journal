package com.sarpuner.journal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       parseMainPage()
    }

    // TODO: Add a scrolling list of RFI episodes.

    // TODO: Implement buttons to download specific episodes.

}
