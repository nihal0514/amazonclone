package com.example.amazonclone.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.amazonclone.R
import com.example.amazonclone.adapters.CategoryListAdapters
import com.example.amazonclone.adapters.ImageSliderAdapter
import com.example.amazonclone.adapters.OnClickListener
import com.example.amazonclone.db.CategoryDB
import com.example.amazonclone.di.ApplicationComponent
import com.example.amazonclone.di.DaggerApplicationComponent
import com.example.amazonclone.model.banner.BannerListItem
import com.example.amazonclone.model.category.CategoryListItem
import com.example.amazonclone.ui.product.Prodpage
import com.example.amazonclone.viewModel.BannerViewModel
import com.example.amazonclone.viewModel.CategoryViewModel
import com.example.amazonclone.viewModel.MainViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var categoryListAdapters: CategoryListAdapters
    private lateinit var categoryListRecyv: RecyclerView
    private lateinit var bannerViewModel: BannerViewModel
    private var bannerList: ArrayList<BannerListItem> = arrayListOf()
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private lateinit var viewpager2: ViewPager2

    private lateinit var applicationComponent: ApplicationComponent
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())
        applicationComponent.inject(this)
        categoryViewModel = ViewModelProvider(this, mainViewModelFactory)[CategoryViewModel::class.java]
        bannerViewModel= ViewModelProvider(this,mainViewModelFactory)[BannerViewModel::class.java]


        viewpager2= view.findViewById<View>(R.id.slide_img) as ViewPager2
        categoryListRecyv= view.findViewById<View>(R.id.topdeals_recyv)as RecyclerView
         categoryViewModel.refresh()
        bannerViewModel.refresh()

        categoryListAdapters = CategoryListAdapters(arrayListOf(), OnClickListener {

            val bundle = Bundle()
            bundle.putString("name", it.name)
            val fragmentManager = childFragmentManager // For an activity, or use childFragmentManager for a fragment
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = Prodpage()
            fragment.arguments= bundle
            fragmentTransaction.replace(R.id.mainfragment,fragment)
            fragmentTransaction.commit()

        })

        imageSliderAdapter= ImageSliderAdapter(bannerList)

        categoryListRecyv.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = categoryListAdapters
        }


        viewpager2.adapter= imageSliderAdapter

        observeBannerModel()
        observeViewModel()
        return view
    }

    private fun observeBannerModel() {
        bannerViewModel.bannerLiveData.observe(viewLifecycleOwner){banner ->
            banner.let {
                imageSliderAdapter.updateBanner(it)
            }

        }
    }

    private fun observeViewModel() {
        categoryViewModel.categoryLiveData.observe(viewLifecycleOwner){categories ->
            categories.let {
                categoryListAdapters.updateCategory(it);
            }

        }
    }
}