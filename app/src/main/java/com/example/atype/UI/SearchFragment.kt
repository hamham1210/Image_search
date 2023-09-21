package com.example.atype.UI

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.atype.data.api.SearchModel
import com.example.atype.databinding.FragmentSearchBinding
import com.example.atype.recyclerview.SearchAdapter

class SearchFragment : Fragment() {
    private val TAG = "SearchFragment"
    private  lateinit var binding: FragmentSearchBinding
    private lateinit var mContext : Context
    private lateinit var adapter: SearchAdapter
    private  var likedItem : List<SearchModel> = listOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = activity as MainActivity
        likedItem = mainActivity.likedItems
        adapter = SearchAdapter(mContext).apply {
            items = likedItem.toMutableList()
        }
      binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
          searchRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
          searchRecyclerView.adapter = adapter
      }
        return binding?.root
    }


}

