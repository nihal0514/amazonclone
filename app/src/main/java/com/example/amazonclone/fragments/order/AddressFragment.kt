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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.amazonclone.R
import com.example.amazonclone.adapters.AdressListAdapter
import com.example.amazonclone.adapters.OnAddressClickListener
import com.example.amazonclone.databinding.FragmentAddressBinding
import com.example.amazonclone.di.ApplicationComponent
import com.example.amazonclone.di.DaggerApplicationComponent
import com.example.amazonclone.viewModel.AddressViewModel
import com.example.amazonclone.viewModel.MainViewModelFactory
import javax.inject.Inject


interface ViewPagerNavigationListener {
    fun navigateToNextPage()
}

class AddressFragment : Fragment() {


    private lateinit var addressListAdapter: AdressListAdapter
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var addressViewModel: AddressViewModel
    private lateinit var applicationComponent: ApplicationComponent
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var binding: FragmentAddressBinding
    private lateinit var token: String

    private var viewPagerNavigationListener: ViewPagerNavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ViewPagerNavigationListener) {
            viewPagerNavigationListener = context
        } else {
            throw IllegalArgumentException("The hosting Activity must implement ViewPagerNavigationListener")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_address,container,false)

        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())
        applicationComponent.inject5(this)
        addressViewModel = ViewModelProvider(this, mainViewModelFactory)[AddressViewModel::class.java]


        sharedPreferences = requireContext().getSharedPreferences("amazonclone", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token",null).toString()

        addressListAdapter= AdressListAdapter(arrayListOf(), OnAddressClickListener { })

        binding.addressesRecyv.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            adapter = addressListAdapter
        }

        binding.findpickupTv.setOnClickListener{
            viewPagerNavigationListener?.navigateToNextPage();
        }
        addressViewModel.refresh(token!!)

        ObserveAddressModel()

        binding.addnewAddressTv.setOnClickListener{
            val fragmentManager = childFragmentManager // For an activity, or use childFragmentManager for a fragment
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = AddNewAddres()
            fragmentTransaction.replace(R.id.address_frame,fragment)
            fragmentTransaction.commit()
        }
        return binding.root
    }
    fun ViewPager2.nextPage(smoothScroll: Boolean = true): Boolean {
        if ((currentItem + 1) < adapter?.itemCount ?: 0) {
            setCurrentItem(currentItem + 1, smoothScroll)
            return true
        }
        //can't move to next page, maybe current page is last or adapter not set.
        return false
    }
    private fun ObserveAddressModel() {
        addressViewModel.addressLiveData.observe(viewLifecycleOwner){
            if(it.size>0){
                binding.addressTv.visibility= View.VISIBLE
            }

            addressListAdapter.updateAddress(it)
        }
    }


}