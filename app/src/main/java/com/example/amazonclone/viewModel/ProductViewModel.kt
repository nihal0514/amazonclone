package com.example.amazonclone.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonclone.model.cart.CartRequest
import com.example.amazonclone.model.category.CategoryListItem
import com.example.amazonclone.model.products.ProdListItem
import com.example.amazonclone.model.products.Product
import com.example.amazonclone.repository.AmazonRepository
import com.example.amazonclone.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val repository: AmazonRepository
) : ViewModel(){

    val prodLiveData: LiveData<UiState<List<ProdListItem>>> = repository.prodListData
    val prodLiveByIdData: LiveData<Product> = repository.prodListByIdData

    fun refresh() {
        fetchProductItems()
    }

    fun prodById(id: String) {
        fetchProductById(id)
    }

    private fun fetchProductById(id: String) {
        viewModelScope.launch {
            repository.getproductsByID(id)
        }
    }

    private fun fetchProductItems() {
        viewModelScope.launch {
            repository.getproducts()
        }
    }

    class OnClickListener(val clickListener: (prodListItem: ProdListItem) -> Unit) {
    }

}