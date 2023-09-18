package com.example.atype

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.atype.databinding.ActivityMainBinding


class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"
    private  lateinit var binding: HomeFragment
//    override fun onCreate(savedInstanceState: Bundle?) = with(binding){
//        super.onCreate(savedInstanceState)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    }
