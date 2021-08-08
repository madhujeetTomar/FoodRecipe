package com.mj.foodrecipe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.mj.foodrecipe.R
import com.mj.foodrecipe.core.util.Constants

class BrowserActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        val detail: WebView = findViewById(R.id.detail_data)

        detail.loadUrl(intent.getStringExtra(Constants.URL)!!)

        detail.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        detail.settings.setSupportZoom(true)

    }
}