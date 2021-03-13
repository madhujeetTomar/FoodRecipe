package com.mj.foodrecipe.core.util.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mj.foodrecipe.models.FoodRecipe

class RecipeTypeConverter {

   private var gson = Gson()
    
    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun dataToFoodRecipe(data: String): FoodRecipe {
        return gson.fromJson(data, FoodRecipe::class.java)
    }
}