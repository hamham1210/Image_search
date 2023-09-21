package com.example.atype.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.atype.R
import com.example.atype.data.api.ImageSearchResponse
import com.example.atype.data.api.SearchModel
import com.example.atype.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var likedItems:ArrayList<SearchModel> = ArrayList()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?)= with(binding) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        navigationItemSelect()
    }

    private fun navigationItemSelect()= with(binding){
        mainNav.run {
            setOnItemReselectedListener {
                item ->
                when(item.itemId){
                    R.id.item_home -> replaceFragment(HomeFragment())
                    R.id.item_search -> replaceFragment(SearchFragment())
                }
                true
            }
            selectedItemId = R.id.main_nav
        }

    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame, fragment)
        fragmentTransaction.commit()

    }
    fun addLikedItem(item: SearchModel){
        if (!likedItems.contains(item)){
            likedItems.add(item)
        }
    }
    fun removeLikedItem(item: SearchModel){
        likedItems.remove(item)
    }

}