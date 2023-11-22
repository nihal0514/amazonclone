package com.example.amazonclone.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonclone.model.stripe.StripeServerModel
import com.example.amazonclone.repository.AmazonRepository
import com.example.amazonclone.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrderViewModel @Inject constructor(
    private val repository: AmazonRepository
) : ViewModel(){
    val paymentData: LiveData<UiState<StripeServerModel>> = repository.getPaymentData

    fun getServer(id: String) {
        getServerVm(id)
    }
    fun setOrder(id: String) {
        setOrderVm(id)
    }
    fun deleteCart(id: String) {
        deleteCartVm(id)
    }

    private fun deleteCartVm(id: String) {
        viewModelScope.launch {
            repository.deleteCart(id)
        }
    }

    private fun setOrderVm(id: String) {
        viewModelScope.launch {
            repository.setOrder(id)
        }

    }

    private fun getServerVm(id: String) {
        viewModelScope.launch {
            repository.getServer(id)
        }
    }

}
