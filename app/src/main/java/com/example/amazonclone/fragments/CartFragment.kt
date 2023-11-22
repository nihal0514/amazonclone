package com.example.amazonclone.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amazonclone.R
import com.example.amazonclone.adapters.CartListAdapter
import com.example.amazonclone.adapters.ItemClickListener
import com.example.amazonclone.adapters.OnCartClickListener
import com.example.amazonclone.adapters.OnClickListener
import com.example.amazonclone.databinding.FragmentCartBinding
import com.example.amazonclone.databinding.FragmentHomeBinding
import com.example.amazonclone.di.ApplicationComponent
import com.example.amazonclone.di.DaggerApplicationComponent
import com.example.amazonclone.model.cart.CartRequest
import com.example.amazonclone.ui.order.OrderAcitivity
import com.example.amazonclone.utils.UiState
import com.example.amazonclone.viewModel.BannerViewModel
import com.example.amazonclone.viewModel.CartViewModel
import com.example.amazonclone.viewModel.CategoryViewModel
import com.example.amazonclone.viewModel.MainViewModelFactory
import javax.inject.Inject

class CartFragment : Fragment(),ItemClickListener {

    private lateinit var cartListAdaper: CartListAdapter
    private lateinit var cartRecyv: RecyclerView
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var cartViewModel: CartViewModel
    private lateinit var applicationComponent: ApplicationComponent
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var binding: FragmentCartBinding
    private lateinit var token: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_cart,container,false)

        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())
        applicationComponent.inject4(this)
        cartViewModel = ViewModelProvider(this, mainViewModelFactory)[CartViewModel::class.java]


        sharedPreferences = requireContext().getSharedPreferences("amazonclone", Context.MODE_PRIVATE)
       token = sharedPreferences.getString("token",null).toString()

        cartListAdaper= CartListAdapter(arrayListOf(), OnCartClickListener{},this)

        binding.cartRecyv.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            adapter = cartListAdaper
        }

        binding.proceedtoBuyBtn.setOnClickListener{
            val intent = Intent (getActivity(), OrderAcitivity::class.java)
            getActivity()?.startActivity(intent)
        }
        cartViewModel.refresh(token!!)

        ObserveCartModel()
        return binding.root
    }

    override fun onPlusItemClick(itemId:String) {
        var a= CartRequest()
        a.itemId= itemId
        a.quantity= 1
        cartViewModel.addtoCart(a,token!!)
    }

    override fun onMinusItemClick(itemId: String) {
        var a= CartRequest()
        a.itemId= itemId
        a.quantity= -1
        cartViewModel.addtoCart(a,token!!)
    }
    private fun ObserveCartModel(){
        cartViewModel.cartLiveData.observe(viewLifecycleOwner){
            when(it){
                is UiState.Success ->{
                    binding.cartShimmer.stopShimmerAnimation()
                    binding.totalbillCart.text= it.data.bill.toString()
                    cartListAdaper.updateCart(it.data.items ?: emptyList())
                }
                is UiState.Loading -> {
                    binding.cartShimmer.startShimmerAnimation()
                }
                is UiState.Error -> {
                    binding.cartShimmer.stopShimmerAnimation()
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}