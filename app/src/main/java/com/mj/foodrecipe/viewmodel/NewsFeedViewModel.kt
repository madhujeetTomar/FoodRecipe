package com.mj.foodrecipe.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mj.foodrecipe.core.db.RecipeEntity
import com.mj.foodrecipe.models.FoodRecipe
import com.mj.foodrecipe.repository.RecipeRepository
import com.mj.foodrecipe.core.util.NetworkResult
import com.mj.foodrecipe.models.NewsFeed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class NewsFeedViewModel @ViewModelInject constructor(
    private val repository: RecipeRepository,
    application: Application
) : AndroidViewModel(application) {


    var recipeData: LiveData<List<RecipeEntity>> = repository.local.readRecipe().asLiveData()

   private fun insertRecipe(recipeEntity: RecipeEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertRecipe(recipeEntity)
        }

    private fun offlineCacheRecipe(recipeData: FoodRecipe) {
        val recipeEntity = RecipeEntity(recipeData)
        insertRecipe(recipeEntity)
    }

    /**RETROFIT**/
    var recipesResponse: MutableLiveData<NetworkResult<NewsFeed>> = MutableLiveData()

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getNewsFeed(queries)
                recipesResponse.value = handleNewsFeedResponse(response)
                val recipeData = recipesResponse.value!!.data

            } catch (e: Exception) {
                recipesResponse.value = NetworkResult.Error(e.message)
            }
        } else {
            recipesResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }
    private fun handleNewsFeedResponse(response: Response<NewsFeed>): NetworkResult<NewsFeed>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
            response.body()!!.articles.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found.")
            }
            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetworkResult.Success(foodRecipes!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }


}