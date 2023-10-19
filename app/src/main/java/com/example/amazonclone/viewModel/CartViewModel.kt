package com.example.amazonclone.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonclone.model.cart.CartRequest
import com.example.amazonclone.model.cart.CartResponse
import com.example.amazonclone.model.cart.GetCartResponse
import com.example.amazonclone.model.cart.ItemsItem
import com.example.amazonclone.repository.AmazonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val repository: AmazonRepository
) : ViewModel() {
    val cartLiveData: LiveData<GetCartResponse> = repository.getCartData

    fun refresh(token: String) {
        fetchCartItems(token)
    }

    fun addtoCart(cartRequest: CartRequest,token: String){
        addtoCartData(cartRequest,token)
    }


    private fun addtoCartData(cartRequest: CartRequest, token: String) {
        viewModelScope.launch {
            repository.addToCart(cartRequest,token)
        }
    }

    private fun fetchCartItems(token: String) {
        viewModelScope.launch {
            repository.getCartData(token)
        }
    }

}
