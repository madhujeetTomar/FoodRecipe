package com.mj.foodrecipe.core.data

import com.mj.foodrecipe.core.db.RecipeDAO
import com.mj.foodrecipe.core.db.RecipeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val recipeDAO: RecipeDAO) {

    suspend fun insertRecipe(recipeEntity: RecipeEntity) {
        recipeDAO.insertRecipe(recipeEntity)
    }

    fun readRecipe(): Flow<List<RecipeEntity>>
    {
       return recipeDAO.readRecipe()
    }

}