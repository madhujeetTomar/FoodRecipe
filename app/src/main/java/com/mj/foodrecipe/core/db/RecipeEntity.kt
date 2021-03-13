package com.mj.foodrecipe.core.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mj.foodrecipe.models.FoodRecipe
import com.mj.foodrecipe.core.util.Constants


@Entity(tableName = Constants.RECIPE_TABLE)
class RecipeEntity(var foodRecipe:FoodRecipe) {
    @PrimaryKey(autoGenerate = false)
    var id=0

}