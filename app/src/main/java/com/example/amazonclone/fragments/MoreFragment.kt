package com.example.amazonclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.amazonclone.R
import com.example.amazonclone.databinding.FragmentMoreBinding
import com.example.amazonclone.utils.apiUtilities
import com.example.amazonclone.utils.interfaceImp
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MoreFragment : Fragment() {

    lateinit var paymentSheet: PaymentSheet

    lateinit var paymentIntent: String
    lateinit var empheralKey: String
    lateinit var customer: String
    lateinit var publishableKey: String


    lateinit var binding: FragmentMoreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_more,container,false)
/*

        PaymentConfiguration.init(requireContext(),PUBLISHABLE_KEY )
        getServer()

        binding.button.setOnClickListener{
            paymentFlow()
        }

        paymentSheet = PaymentSheet(this,::onPaymentSheetResult)
*/

        return binding.root
    }
    private fun paymentFlow() {
        paymentSheet.presentWithPaymentIntent(
            paymentIntent,
            PaymentSheet.Configuration(
                "Nihal",
                PaymentSheet.CustomerConfiguration(
                    customer,empheralKey
                )
            )
        )
    }

    private var apiInterface: interfaceImp = apiUtilities.getApiInterface()

    private fun getServer() {
        lifecycleScope.launch (Dispatchers.IO){
            val res= apiInterface.getfromserver()
            withContext(Dispatchers.Main){
                if(res.isSuccessful && res.body()!=null){
                    customer= res.body()!!.customer!!
                    paymentIntent= res.body()!!.paymentIntent!!
                    empheralKey= res.body()!!.ephemeralKey!!
                    publishableKey= res.body()!!.publishableKey!!

                    Toast.makeText(requireContext(),"Proceed for payment", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
    fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        // implemented in the next steps
        if(paymentSheetResult is PaymentSheetResult.Completed){
            Toast.makeText(requireContext(),"Payment Done", Toast.LENGTH_SHORT).show()
        }
    }

}