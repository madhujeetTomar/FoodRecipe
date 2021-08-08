package com.mj.foodrecipe.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mj.foodrecipe.R
import com.mj.foodrecipe.core.util.Constants
import com.mj.foodrecipe.view.adapter.RecipesAdapter
import com.mj.foodrecipe.core.util.NetworkResult
import com.mj.foodrecipe.view.adapter.NewsFeedAdapter
import com.mj.foodrecipe.viewmodel.MainViewModel
import com.mj.foodrecipe.viewmodel.NewsFeedViewModel
import com.mj.foodrecipe.viewmodel.RecipesViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [NewsFeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class NewsFeedFragment : Fragment(), NewsFeedAdapter.ClickListener {
    private val TAG = "NewsFeedFragment"
    private lateinit var mainViewModel: NewsFeedViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val mAdapter by lazy { NewsFeedAdapter(this) }
    private lateinit var mView: View
    private lateinit var recyclerview: ShimmerRecyclerView

    companion object {
        fun newInstance() = NewsFeedFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(NewsFeedViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_recipes, container, false)
        recyclerview= mView.findViewById<ShimmerRecyclerView>(R.id.recyclerview)
        setupRecyclerView()
        readData()
        return mView
    }

    private fun readData() {

        requestApiData()
     }

    private fun requestApiData() {
        Log.d(TAG, "readData: "+"readOnline")
        mainViewModel.getRecipes(recipesViewModel.applyNewsFeedQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            when(response){
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading ->{
                    showShimmerEffect()
                }
            }
        })
    }

    private fun setupRecyclerView() {

       recyclerview.adapter = mAdapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        recyclerview.showShimmer()
    }

    private fun hideShimmerEffect() {
        recyclerview.hideShimmer()
    }

    override fun onViewClick(url: String?) {
        var intent =Intent(activity,BrowserActivity::class.java)
        intent.putExtra(Constants.URL,url)

activity?.startActivity(intent)
    }
}