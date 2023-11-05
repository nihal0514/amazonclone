package com.example.amazonclone.fragments.order

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.amazonclone.R
import com.example.amazonclone.databinding.FragmentAddNewAddresBinding
import com.example.amazonclone.di.ApplicationComponent
import com.example.amazonclone.di.DaggerApplicationComponent
import com.example.amazonclone.model.address.AddAddressRequest
import com.example.amazonclone.viewModel.AddressViewModel
import com.example.amazonclone.viewModel.MainViewModelFactory
import javax.inject.Inject

class AddNewAddres : Fragment() {

    lateinit var sharedPreferences: SharedPreferences
    private lateinit var addressViewModel: AddressViewModel
    private lateinit var applicationComponent: ApplicationComponent
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var binding: FragmentAddNewAddresBinding
    private lateinit var token: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_add_new_addres,container,false)

        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())
        applicationComponent.inject6(this)
        addressViewModel = ViewModelProvider(this, mainViewModelFactory)[AddressViewModel::class.java]


        sharedPreferences = requireContext().getSharedPreferences("amazonclone", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token",null).toString()

        binding.btnAddAddress.setOnClickListener{
            addNewAddress()
        }
        return binding.root
    }

    private fun addNewAddress() {
        var address= AddAddressRequest()
        address.address= binding.fulladdressAddAddress.text.toString()
        address.name= binding.nameAddAddress.text.toString()
        address.pin= Integer.parseInt(binding.pinAddAddress.text.toString())
        address.mobile= Integer.parseInt(binding.mobileAddAddress.text.toString())
        address.state= binding.stateAddAddress.text.toString()
        address.country= binding.countryAddAddress.text.toString()

        addressViewModel.addAddress(address,token)
    }
}