package com.mj.foodrecipe.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mj.foodrecipe.databinding.RecipeRowLayoutBinding
import com.mj.foodrecipe.models.FoodRecipe
import com.mj.foodrecipe.core.util.RecipeDiffUtils
import com.mj.foodrecipe.models.Result

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    private var list = emptyList<Result>()


    class RecipesViewHolder(private val binding: RecipeRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            binding.model = result
    binding.executePendingBindings()
        }

        companion object {
        fun from(parent : ViewGroup): RecipesViewHolder
            {
            val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipeRowLayoutBinding.inflate(layoutInflater, parent,false)
                return RecipesViewHolder(binding)

        }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
       return RecipesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
       val currentResult = list[position]
        holder.bind(currentResult)

    }

    fun setData(newData: FoodRecipe)
    {
        val recipeDiffUtils: RecipeDiffUtils<Result> = RecipeDiffUtils(list,newData.results)
        val diffUtilResult=DiffUtil.calculateDiff(recipeDiffUtils)
     list = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }
    override fun getItemCount(): Int {
       return list.size
    }
}