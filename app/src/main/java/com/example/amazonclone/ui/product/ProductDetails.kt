package com.example.amazonclone.ui.product

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.amazonclone.R
import com.example.amazonclone.adapters.ProdPageAdapter
import com.example.amazonclone.di.ApplicationComponent
import com.example.amazonclone.di.DaggerApplicationComponent
import com.example.amazonclone.model.cart.CartRequest
import com.example.amazonclone.model.products.ProdListItem
import com.example.amazonclone.utils.getProgressDrawable
import com.example.amazonclone.utils.loadImage
import com.example.amazonclone.utils.loadImage1
import com.example.amazonclone.viewModel.CartViewModel
import com.example.amazonclone.viewModel.MainViewModelFactory
import com.example.amazonclone.viewModel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product_details.image_prodDetails
import javax.inject.Inject

class ProductDetails : Fragment() {
    private lateinit var productViewModel: ProductViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var applicationComponent: ApplicationComponent

    private lateinit var name: TextView
    private lateinit var descp: TextView
    private lateinit var price: TextView
    private lateinit var prodImage: ImageView
    private lateinit var addtoCart_prodDetail: TextView
    lateinit var sharedPreferences: SharedPreferences


    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_product_details, container, false)

        sharedPreferences = requireContext().getSharedPreferences("amazonclone", Context.MODE_PRIVATE)


        val token = sharedPreferences.getString("token",null)


        val id= arguments?.getString("id")

        name= view.findViewById<View>(R.id.name_prodDetails)as TextView
        descp= view.findViewById<View>(R.id.descp_prodDetails)as TextView
        price= view.findViewById<View>(R.id.price_prodDetails)as TextView
        addtoCart_prodDetail= view.findViewById<View>(R.id.addtocart_prodDetail)as TextView
        prodImage= view.findViewById<View>(R.id.image_prodDetails)as ImageView

        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())
        applicationComponent.inject3(this)
        productViewModel = ViewModelProvider(this, mainViewModelFactory)[ProductViewModel::class.java]
        cartViewModel = ViewModelProvider(this, mainViewModelFactory)[CartViewModel::class.java]


        productViewModel.prodById(id !!)

        addtoCart_prodDetail.setOnClickListener{

            var a= CartRequest()
            a.itemId= id
            a.quantity= 1
            cartViewModel.addtoCart(a,token!!)

        }

        observeProdDetails()

        return view
    }

    private fun observeProdDetails() {
        productViewModel.prodLiveByIdData.observe(viewLifecycleOwner){ prodDetails ->
            prodDetails.let {
                name.text= it.name
                price.text= it.price.toString()
                descp.text= it.description
                prodImage.loadImage1(it.images?.get(0) )
            }
        }
    }

}