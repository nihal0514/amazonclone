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
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.amazonclone.R
import com.example.amazonclone.adapters.CategoryListAdapters
import com.example.amazonclone.adapters.ImageSliderAdapter
import com.example.amazonclone.adapters.OnClickListener
import com.example.amazonclone.databinding.FragmentHomeBinding
import com.example.amazonclone.db.CategoryDB
import com.example.amazonclone.di.ApplicationComponent
import com.example.amazonclone.di.DaggerApplicationComponent
import com.example.amazonclone.model.banner.BannerListItem
import com.example.amazonclone.model.category.CategoryListItem
import com.example.amazonclone.ui.product.Prodpage
import com.example.amazonclone.utils.UiState
import com.example.amazonclone.viewModel.BannerViewModel
import com.example.amazonclone.viewModel.CategoryViewModel
import com.example.amazonclone.viewModel.MainViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var categoryListAdapters: CategoryListAdapters
    private lateinit var bannerViewModel: BannerViewModel
    private var bannerList: ArrayList<BannerListItem> = arrayListOf()
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private lateinit var applicationComponent: ApplicationComponent
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())
        applicationComponent.inject(this)
        categoryViewModel = ViewModelProvider(this, mainViewModelFactory)[CategoryViewModel::class.java]
        bannerViewModel= ViewModelProvider(this,mainViewModelFactory)[BannerViewModel::class.java]

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

        binding.topdealsRecyv.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = categoryListAdapters
        }


        binding.slideImg.adapter= imageSliderAdapter

        observeBannerModel()
        observeViewModel()
        return binding.root
    }

    private fun observeBannerModel() {
        bannerViewModel.bannerLiveData.observe(viewLifecycleOwner){banner ->
            banner.let {
                when(it){
                    is UiState.Success ->{
                   //     binding.addressProgressBar.visibility = View.GONE
                        imageSliderAdapter.updateBanner(it.data)
                    }
                    is UiState.Loading -> {
                     //   binding.addressProgressBar.visibility = View.VISIBLE
                    }
                    is UiState.Error -> {
                        //Handle Error
                    //    binding.addressProgressBar.visibility = View.GONE
                     //   Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                }

            }

        }
    }

    private fun observeViewModel() {
        categoryViewModel.categoryLiveData.observe(viewLifecycleOwner){categories ->
            categories.let {
                when(it){
                    is UiState.Success ->{
                        categoryListAdapters.updateCategory(it.data);
                    }
                    is UiState.Loading -> {
                    //    binding.addressProgressBar.visibility = View.VISIBLE
                    }
                    is UiState.Error -> {
                        //Handle Error
                   //     binding.addressProgressBar.visibility = View.GONE
                     //   Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                }


            }

        }
    }
}