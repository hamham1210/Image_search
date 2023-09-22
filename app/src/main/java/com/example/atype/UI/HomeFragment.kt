package com.example.atype.UI

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.atype.data.api.Constants
import com.example.atype.data.api.Content
import com.example.atype.data.api.ImageSearchResponse
import com.example.atype.data.api.RetrofitClient.apiService
import com.example.atype.data.api.SearchModel
import com.example.atype.databinding.FragmentHomeBinding
import com.example.atype.recyclerview.HomeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import androidx.appcompat.widget.SearchView


class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"
    private  lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private lateinit var gridmanager: StaggeredGridLayoutManager
    private  var resItems : ArrayList<SearchModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        SetupView()
        setupListeners()
        return binding.root
    }
    private fun SetupView(){
        val gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.homeRecyclerView.layoutManager = gridmanager
        adapter = HomeAdapter(requireContext())
        binding.homeRecyclerView.adapter = adapter
        binding.homeRecyclerView.itemAnimator = null
        val lastSearch = Content.saveLastSearch(requireContext(), "defaultQuery")
        val editableText = Editable.Factory.getInstance().newEditable(lastSearch.toString())
        binding.mainSearchBar.text =editableText
    }
    private fun setupListeners(){
        binding.mainBtn.setOnClickListener{
            val query = binding.mainSearchBar.text.toString()
            if (query.isNotEmpty()){
                Content.saveLastSearch(requireContext(), query)
                fetchImageResults(query)
                adapter.clearItem()
            }else {
                Toast.makeText(requireContext(),"검색어를 입력해주세요.",Toast.LENGTH_LONG).show()
            }
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
            imm.hideSoftInputFromWindow(binding.mainSearchBar.windowToken, 0)
        }
    }
private fun fetchImageResults(query: String){
    apiService.getSearchImage(apiKey =Constants.AUTH_HEADER, query = query,sort="accuracy", page = 1, size = 30)?.enqueue(object : Callback<ImageSearchResponse?>{
        override fun onResponse(
            call: Call<ImageSearchResponse?>,
            response: Response<ImageSearchResponse?>
        ) {
            val body = response.body()
           body?.metaData?.let { metaData ->
                if (metaData?.totalCount ?: 0 > 0){
                    body!!.documents?.forEach { document ->
                        val title = document.sitename
                        val dateTime = document.datetime
                        val url =document.thumbnail_url
                        resItems.add(SearchModel(title,dateTime,url))
                    }
                }
            }
            adapter.items =  resItems
            adapter.notifyDataSetChanged()
        }

        override fun onFailure(call: Call<ImageSearchResponse?>, t: Throwable) {
            Log.e("tag","onFailure: ${t.message}")
        }
    })
}



}
