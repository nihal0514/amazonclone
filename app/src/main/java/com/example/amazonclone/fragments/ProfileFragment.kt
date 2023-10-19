package com.example.amazonclone.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.amazonclone.R
import com.example.amazonclone.databinding.FragmentHomeBinding
import com.example.amazonclone.databinding.FragmentProfileBinding
import com.example.amazonclone.ui.HomeActivity
import com.example.amazonclone.ui.signin.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.btn_signin
class ProfileFragment : Fragment() {
    private lateinit var btn_signin: AppCompatButton
    private lateinit var before_signin: RelativeLayout
    private lateinit var after_signin: RelativeLayout
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var profile_name: TextView

    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)
        sharedPreferences = requireContext().getSharedPreferences("amazonclone", Context.MODE_PRIVATE)

        val token = sharedPreferences.getString("token",null)
        val name = sharedPreferences.getString("name",null)
        if(token!= null){
            binding.beforesigninProfile.visibility= View.GONE
            binding.aftersigninProfile.visibility= View.VISIBLE
            binding.profileName.text= name
        }

        binding.btnSignin.setOnClickListener{
            val intent = Intent (getActivity(), LoginActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        return binding.root
    }
}