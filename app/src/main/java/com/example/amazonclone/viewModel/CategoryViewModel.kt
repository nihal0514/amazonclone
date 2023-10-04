package com.example.amazonclone.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonclone.model.category.CategoryListItem
import com.example.amazonclone.repository.AmazonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val repository: AmazonRepository
) :ViewModel(){
    val categoryLiveData: LiveData<List<CategoryListItem>> = repository.categoryDeals

    fun refresh() {
       fetchCategoryDeals()
    }

    private fun fetchCategoryDeals() {
        viewModelScope.launch {
            repository.getCategories()
        }
    }

}

class TestForMultiBinding @Inject constructor() {
    fun testSomething() {

    }
}