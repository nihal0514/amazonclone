package com.example.amazonclone.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonclone.model.address.AddAddressRequest
import com.example.amazonclone.model.address.AddressItem
import com.example.amazonclone.model.cart.CartRequest
import com.example.amazonclone.repository.AmazonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddressViewModel @Inject constructor(
    private val repository: AmazonRepository
): ViewModel(){
    val addressLiveData: LiveData<List<AddressItem>> = repository.getAddress

    fun refresh(token: String) {
        fetchAddressItems(token)
    }

    private fun fetchAddressItems(token: String) {
        viewModelScope.launch {
            repository.getAddress(token)
        }

    }

    fun addAddress(addAddressRequest: AddAddressRequest,token: String){
        addAddressData(addAddressRequest,token)
    }


    private fun addAddressData(addAddressRequest: AddAddressRequest, token: String) {
        viewModelScope.launch {
            repository.addAddress(addAddressRequest,token)
        }
    }
}