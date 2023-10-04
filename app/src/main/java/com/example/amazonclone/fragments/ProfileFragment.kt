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
import androidx.viewpager2.widget.ViewPager2
import com.example.amazonclone.R
import com.example.amazonclone.ui.HomeActivity
import com.example.amazonclone.ui.signin.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.btn_signin
class ProfileFragment : Fragment() {
    private lateinit var btn_signin: AppCompatButton
    private lateinit var before_signin: RelativeLayout
    private lateinit var after_signin: RelativeLayout
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var profile_name: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        sharedPreferences = requireContext().getSharedPreferences("amazonclone", Context.MODE_PRIVATE)

        before_signin= view.findViewById<View>(R.id.beforesignin_profile)as RelativeLayout
        after_signin= view.findViewById<View>(R.id.aftersignin_profile)as RelativeLayout
        btn_signin= view.findViewById<View>(R.id.btn_signin)as AppCompatButton
        profile_name= view.findViewById<View>(R.id.profile_name)as TextView

        val token = sharedPreferences.getString("token",null)
        val name = sharedPreferences.getString("name",null)
        if(token!= null){
            before_signin.visibility= View.GONE
            after_signin.visibility= View.VISIBLE
            profile_name.text= name
        }

        btn_signin.setOnClickListener{
            val intent = Intent (getActivity(), LoginActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        return view
    }
}