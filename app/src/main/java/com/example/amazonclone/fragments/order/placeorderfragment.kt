package com.example.amazonclone.fragments.order

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.amazonclone.R
import com.example.amazonclone.databinding.FragmentAddressBinding
import com.example.amazonclone.databinding.FragmentPlaceorderfragmentBinding
import com.example.amazonclone.ui.home.HomeActivity

class placeorderfragment : Fragment() {

    lateinit var binding: FragmentPlaceorderfragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_placeorderfragment,container,false)

        binding.btnPlaceOrder.setOnClickListener{
            val intent = Intent (getActivity(), HomeActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        return binding.root
    }
}