package com.mj.foodrecipe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mj.foodrecipe.core.util.Constants.Companion.NEWS_FEED_API_KEY
import com.mj.foodrecipe.core.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.mj.foodrecipe.core.util.Constants.Companion.QUERY_API_KEY
import com.mj.foodrecipe.core.util.Constants.Companion.QUERY_COUNTRY
import com.mj.foodrecipe.core.util.Constants.Companion.QUERY_DIET
import com.mj.foodrecipe.core.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.mj.foodrecipe.core.util.Constants.Companion.QUERY_NUMBER
import com.mj.foodrecipe.core.util.Constants.Companion.QUERY_TYPE


class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = "68075518137d41ab92a61a21fb1e6172"
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

    fun applyNewsFeedQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_COUNTRY] = "in"
        queries[QUERY_API_KEY] = NEWS_FEED_API_KEY

        return queries
    }

}