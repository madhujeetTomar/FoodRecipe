package com.mj.foodrecipe.core.di

import android.content.Context
import androidx.room.Room
import com.mj.foodrecipe.core.db.RecipeDatabase
import com.mj.foodrecipe.core.util.Constants.Companion.RECIPE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            RecipeDatabase::class.java,
            RECIPE_DATABASE
        ).build()

    @Singleton
    @Provides
    fun provideDatabaseDao(recipeDatabase: RecipeDatabase) = recipeDatabase.recipeDao()
}