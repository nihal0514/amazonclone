package com.example.amazonclone.fragments.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.amazonclone.R
import com.example.amazonclone.databinding.FragmentCartBinding
import com.example.amazonclone.databinding.FragmentDeliveryTypeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DeliveryTypeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeliveryTypeFragment : Fragment() {
    lateinit var binding: FragmentDeliveryTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_delivery_type,container,false)

        binding.radiaId1.isChecked= true
        return binding.root
    }

}