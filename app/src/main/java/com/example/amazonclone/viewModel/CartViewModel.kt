package com.example.amazonclone.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonclone.model.cart.CartRequest
import com.example.amazonclone.model.cart.CartResponse
import com.example.amazonclone.model.cart.GetCartResponse
import com.example.amazonclone.model.cart.ItemsItem
import com.example.amazonclone.repository.AmazonRepository
import com.example.amazonclone.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val repository: AmazonRepository
) : ViewModel() {
    val cartLiveData: LiveData<UiState<GetCartResponse>> = repository.getCartData
    val addtoCartLiveData: LiveData<UiState<CartResponse>> = repository.addtoCartData

    fun refresh(token: String) {
        fetchCartItems(token)
    }

    fun addtoCart(cartRequest: CartRequest,token: String){
        viewModelScope.launch {
            addtoCartData(cartRequest,token)
            fetchCartItems(token)
        }
    }


    private fun addtoCartData(cartRequest: CartRequest, token: String) {

        //you can either use rxjava3 or kotlin coroutines
        repository.addToCart(cartRequest,token)
//        viewModelScope.launch {
//            repository.addToCart(cartRequest,token)
//        }
    }

    private fun fetchCartItems(token: String) {
        repository.getCartData(token)
//        viewModelScope.launch {
//            repository.getCartData(token)
//        }
    }
}
