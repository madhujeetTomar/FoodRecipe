package com.mj.foodrecipe.core.data

import com.mj.foodrecipe.models.FoodRecipe
import com.mj.foodrecipe.core.network.FoodRecipesApi
import com.mj.foodrecipe.models.NewsFeed
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject
constructor(private val foodRecipesApi: FoodRecipesApi) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun getNewsFeed(queries: Map<String, String>): Response<NewsFeed> {
        return foodRecipesApi.getNewsFeed(queries)
    }
}