package com.example.amazonclone.ui.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amazonclone.R
import com.example.amazonclone.adapters.OnProdClickListener
import com.example.amazonclone.adapters.ProdPageAdapter
import com.example.amazonclone.di.ApplicationComponent
import com.example.amazonclone.di.DaggerApplicationComponent
import com.example.amazonclone.model.products.ProdListItem
import com.example.amazonclone.viewModel.MainViewModelFactory
import com.example.amazonclone.viewModel.ProductViewModel
import javax.inject.Inject

class Prodpage : Fragment() {

    private lateinit var prodPageAdapter: ProdPageAdapter
    private lateinit var productViewModel: ProductViewModel
    private lateinit var prodListRecyv: RecyclerView
    private lateinit var prodListItem: ArrayList<ProdListItem>
    private lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_prodpage, container, false)

        val categoryName= arguments?.getString("name")

        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())
        applicationComponent.inject2(this)
        productViewModel = ViewModelProvider(this, mainViewModelFactory)[ProductViewModel::class.java]

        prodListRecyv= view.findViewById<View>(R.id.prodPageRecyv)as RecyclerView

        prodListItem= arrayListOf()

        prodPageAdapter = ProdPageAdapter(arrayListOf(),categoryName!!,OnProdClickListener{
            val bundle = Bundle()
            bundle.putString("id",it.id)
            val fragmentManager = childFragmentManager // For an activity, or use childFragmentManager for a fragment
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = ProductDetails()
            fragment.arguments= bundle
            fragmentTransaction.replace(R.id.prodpage,fragment)
            fragmentTransaction.commit()
        })
        prodListRecyv.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = prodPageAdapter
        }

        productViewModel.refresh()
        observeProdViewModel(categoryName)
        return view
    }

    private fun observeProdViewModel(categoryname: String) {
        productViewModel.prodLiveData.observe(viewLifecycleOwner){products ->
            products.let {
                for(i in 0..(it.size-1)){
                    if(it[i].category?.name == categoryname){
                        prodListItem.add(it[i])
                        Log.d("hii",it[i].category?.name.toString())
                    }
                }
                prodPageAdapter.updateProducts(prodListItem)

            }
        }
    }
}