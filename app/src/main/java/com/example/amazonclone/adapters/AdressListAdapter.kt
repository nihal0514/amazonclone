package com.example.amazonclone.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazonclone.databinding.ItemAddrressesBinding
import com.example.amazonclone.databinding.ItemCartBinding
import com.example.amazonclone.model.address.AddressItem
import com.example.amazonclone.model.cart.ItemsItems
import com.example.amazonclone.utils.getProgressDrawable

@SuppressLint("NotifyDataSetChanged")
class AdressListAdapter (
    private val addressList: ArrayList<AddressItem>, private val onAddressClickListener: OnAddressClickListener
        ): RecyclerView.Adapter<AdressListAdapter.ViewHolder>(){
    class ViewHolder(private val binding: ItemAddrressesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(addressItem: AddressItem) {
            binding.address = addressItem;
        }
    }
    fun updateAddress(newAddressList: List<AddressItem>) {
        addressList.clear()
        addressList.addAll(newAddressList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddrressesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(addressList[position])
    }
}

class OnAddressClickListener(val clickListener: (addressItem: AddressItem) -> Unit) {
    fun onClick(addressItem: AddressItem) = clickListener(addressItem)
}
