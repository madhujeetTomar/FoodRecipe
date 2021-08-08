package com.mj.foodrecipe.core.network

import com.mj.foodrecipe.models.NewsFeed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsFeedApi {
    @GET("top-headlines")
    suspend fun getNewsFeed(
        @QueryMap queries: Map<String, String>
    ): Response<NewsFeed>

}