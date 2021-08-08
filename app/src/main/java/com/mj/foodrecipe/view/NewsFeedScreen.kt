package com.mj.foodrecipe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mj.foodrecipe.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFeedScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_feed_screen)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,
                NewsFeedFragment.newInstance()
            )
            .commit()
    }
}