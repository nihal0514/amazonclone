package com.example.amazonclone.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.amazonclone.R
import com.example.amazonclone.fragments.CartFragment
import com.example.amazonclone.fragments.HomeFragment
import com.example.amazonclone.fragments.MenuFragment
import com.example.amazonclone.fragments.MoreFragment
import com.example.amazonclone.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.bottomNav

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadFragment(HomeFragment())

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.menu_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                R.id.menu_more -> {
                    loadFragment(MoreFragment())
                    true
                }
                R.id.menu_shoppingCart -> {
                    loadFragment(CartFragment())
                    true
                }
                R.id.menu_Menu -> {
                    loadFragment(MenuFragment())
                    true
                }
                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragments,fragment)
        transaction.commit()
    }
}