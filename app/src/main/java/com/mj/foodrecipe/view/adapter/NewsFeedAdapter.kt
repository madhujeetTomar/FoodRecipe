package com.mj.foodrecipe.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mj.foodrecipe.databinding.RecipeRowLayoutBinding
import com.mj.foodrecipe.models.FoodRecipe
import com.mj.foodrecipe.core.util.RecipeDiffUtils
import com.mj.foodrecipe.databinding.NewsFeedLayoutBinding
import com.mj.foodrecipe.models.Article
import com.mj.foodrecipe.models.NewsFeed
import com.mj.foodrecipe.models.Result

class NewsFeedAdapter(val listener : ClickListener) : RecyclerView.Adapter<NewsFeedAdapter.RecipesViewHolder>() {

    private var list = emptyList<Article>()


    class RecipesViewHolder(private val binding: NewsFeedLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Article) {
            binding.model = result
    binding.executePendingBindings()
        }

        companion object {
        fun from(parent : ViewGroup): RecipesViewHolder
            {
            val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NewsFeedLayoutBinding.inflate(layoutInflater, parent,false)
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
        holder.itemView.setOnClickListener {
            listener.onViewClick(list[position].url)
        }

    }

    fun setData(newData: NewsFeed)
    {
        val recipeDiffUtils: RecipeDiffUtils<Article> = RecipeDiffUtils(list,newData.articles)
        val diffUtilResult=DiffUtil.calculateDiff(recipeDiffUtils)
     list = newData.articles
        diffUtilResult.dispatchUpdatesTo(this)
    }
    override fun getItemCount(): Int {
       return list.size
    }

    interface ClickListener
    {
        fun onViewClick(url:String?)
    }
}