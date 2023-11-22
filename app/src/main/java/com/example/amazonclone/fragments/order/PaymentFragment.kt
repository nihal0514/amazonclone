package com.example.amazonclone.fragments.order

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.amazonclone.R
import com.example.amazonclone.databinding.FragmentPaymentBinding
import com.example.amazonclone.di.ApplicationComponent
import com.example.amazonclone.di.DaggerApplicationComponent
import com.example.amazonclone.utils.Constants.PUBLISHABLE_KEY
import com.example.amazonclone.utils.UiState
import com.example.amazonclone.viewModel.MainViewModelFactory
import com.example.amazonclone.viewModel.OrderViewModel
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import javax.inject.Inject


class PaymentFragment : Fragment() {

    private lateinit var orderViewModel: OrderViewModel

    private lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var paymentSheet: PaymentSheet

    lateinit var paymentIntent: String
    lateinit var empheralKey: String
    lateinit var customer: String
    lateinit var publishableKey: String

    lateinit var sharedPreferences: SharedPreferences
    lateinit var  token: String;


    lateinit var binding: FragmentPaymentBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false)


        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())
        applicationComponent.inject7(this)
        orderViewModel = ViewModelProvider(this, mainViewModelFactory)[OrderViewModel::class.java]

        sharedPreferences =
            requireContext().getSharedPreferences("amazonclone", Context.MODE_PRIVATE)

        token = sharedPreferences.getString("token", null).toString()

        PaymentConfiguration.init(requireContext(), PUBLISHABLE_KEY)
        orderViewModel.getServer(token!!)
        getPaymentServerData()

        binding.btnPaymentsubmit.setOnClickListener {
            Toast.makeText(requireContext(), "Please Select any Payment Method", Toast.LENGTH_SHORT).show()

        }

        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)

        binding.paymentGroupradio.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
               when(checkedId){
                   R.id.striperadio ->{
                       binding.btnPaymentsubmit.setOnClickListener {
                           paymentFlow()
                       }
                   }
               }
            })
        binding.striperadio.isChecked= true

        return binding.root
    }

    private fun getPaymentServerData() {
        orderViewModel.paymentData.observe(viewLifecycleOwner){
            when(it){
                is UiState.Success ->{
                    paymentIntent= it.data.paymentIntent!!
                    empheralKey= it.data.ephemeralKey!!
                    customer= it.data.customer!!
                    publishableKey= it.data.publishableKey!!
                }
                is UiState.Loading -> {
//                    binding.addressProgressBar.visibility = View.VISIBLE
                }
                is UiState.Error -> {
//                    //Handle Error
//                    binding.addressProgressBar.visibility = View.GONE
//                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }



        }
    }

    private fun paymentFlow() {
        paymentSheet.presentWithPaymentIntent(
            paymentIntent,
            PaymentSheet.Configuration(
                "Nihal",
                PaymentSheet.CustomerConfiguration(
                    customer, empheralKey
                )
            )
        )
    }

    fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        // implemented in the next steps
        if (paymentSheetResult is PaymentSheetResult.Completed) {
            Toast.makeText(requireContext(), "Payment Done", Toast.LENGTH_SHORT).show()

            orderViewModel.setOrder(token!!)
            orderViewModel.deleteCart(token!!)

            viewPagerNavigationListener?.navigateToNextPage()

        }
    }

}