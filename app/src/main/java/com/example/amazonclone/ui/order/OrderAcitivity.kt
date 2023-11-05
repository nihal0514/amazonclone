package com.example.amazonclone.ui.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.amazonclone.R
import com.example.amazonclone.fragments.order.AddressFragment
import com.example.amazonclone.fragments.order.DeliveryTypeFragment
import com.example.amazonclone.fragments.order.PaymentFragment
import com.example.amazonclone.fragments.order.ViewPagerNavigationListener
import com.example.amazonclone.fragments.order.placeorderfragment
import com.example.amazonclone.ui.signin.SignUpFragment
import com.google.android.material.tabs.TabLayout

class OrderAcitivity : AppCompatActivity(), ViewPagerNavigationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        setSupportActionBar(findViewById(R.id.order_toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var tab_viewpager = findViewById<ViewPager>(R.id.tab_viewpager_order)
        var tab_tablayout = findViewById<TabLayout>(R.id.tab_tablayout_order)
        setupViewPager(tab_viewpager)




        // If we dont use setupWithViewPager() method then
        // tabs are not used or shown when activity opened
        tab_tablayout.setupWithViewPager(tab_viewpager)

    }
    private fun setupViewPager(tabViewpager: ViewPager?) {
        var adapter: ViewPagerAdapter2 = ViewPagerAdapter2(supportFragmentManager)

        // LoginFragment is the name of Fragment and the Login
        // is a title of tab
        adapter.addFragment(AddressFragment (), "Address")
        adapter.addFragment(DeliveryTypeFragment(), "Delivery")
        adapter.addFragment(PaymentFragment(), "Payment")
        adapter.addFragment(placeorderfragment(), "Place order")

        // setting adapter to view pager.
        tabViewpager?.setAdapter(adapter)
    }
    class ViewPagerAdapter2 : FragmentPagerAdapter {

        // objects of arraylist. One is of Fragment type and
        // another one is of String type.*/
        private final var fragmentList1: ArrayList<Fragment> = ArrayList()
        private final var fragmentTitleList1: ArrayList<String> = ArrayList()

        // this is a secondary constructor of ViewPagerAdapter class.
        public constructor(supportFragmentManager: FragmentManager)
                : super(supportFragmentManager)

        // returns which item is selected from arraylist of fragments.
        override fun getItem(position: Int): Fragment {
            return fragmentList1.get(position)
        }

        // returns which item is selected from arraylist of titles.
        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList1.get(position)
        }

        // returns the number of items present in arraylist.
        override fun getCount(): Int {
            return fragmentList1.size
        }

        // this function adds the fragment and title in 2 separate  arraylist.
        fun addFragment(fragment: Fragment, title: String) {
            fragmentList1.add(fragment)
            fragmentTitleList1.add(title)
        }
    }

    override fun navigateToNextPage() {
        val viewPager = findViewById<ViewPager>(R.id.tab_viewpager_order)
        val nextPageIndex = viewPager.currentItem + 1
        viewPager.setCurrentItem(nextPageIndex, true) // Use 'true' for smooth scroll, 'false' for instant switch

    }


}
