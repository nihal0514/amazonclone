package com.example.amazonclone.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amazonclone.R
import com.example.amazonclone.adapters.CategoryListAdapters
import com.example.amazonclone.adapters.OnClickListener
import com.example.amazonclone.db.CategoryDB
import com.example.amazonclone.di.ApplicationComponent
import com.example.amazonclone.di.DaggerApplicationComponent
import com.example.amazonclone.model.CategoryListItem
import com.example.amazonclone.viewModel.CategoryViewModel
import com.example.amazonclone.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.location_tv
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var categoryDealsList: ArrayList<CategoryListItem>
    private lateinit var categoryListAdapters: CategoryListAdapters
    private lateinit var categoryListRecyv: RecyclerView
    private lateinit var categoryDB: CategoryDB;


    private lateinit var applicationComponent: ApplicationComponent
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())
        applicationComponent.inject(this)
        categoryViewModel = ViewModelProvider(this, mainViewModelFactory)[CategoryViewModel::class.java]

        categoryListRecyv= view.findViewById<View>(R.id.topdeals_recyv)as RecyclerView

        categoryViewModel.refresh()

        categoryListAdapters = CategoryListAdapters(arrayListOf(), OnClickListener {
        })

        categoryListRecyv.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = categoryListAdapters
        }
        observeViewModel()
        return view
    }
    private fun observeViewModel() {
        categoryViewModel.categoryLiveData.observe(viewLifecycleOwner){categories ->
            categories.let {
                categoryListAdapters.updateCategory(it);
            }

        }
    }
}