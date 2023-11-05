package com.example.amazonclone.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import com.example.amazonclone.R
import com.example.amazonclone.databinding.FragmentCartBinding
import com.example.amazonclone.databinding.FragmentProfileBinding
import com.example.amazonclone.ui.signin.LoginActivity

class ProfileFragment : Fragment() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)

        sharedPreferences = requireContext().getSharedPreferences("amazonclone", Context.MODE_PRIVATE)

        binding.btnSignin.setOnClickListener{
            val intent = Intent (getActivity(), LoginActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        val token = sharedPreferences.getString("token",null)
        val name = sharedPreferences.getString("name",null)
        Log.d("mmm",token.toString());
        if(token!= null){
            binding.beforesigninProfile.visibility= View.GONE
            binding.aftersigninProfile.visibility= View.VISIBLE
            binding.profileName.text= name
        }


        return binding.root

    }
}