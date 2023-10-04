package com.example.amazonclone.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonclone.model.banner.BannerListItem
import com.example.amazonclone.repository.AmazonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class BannerViewModel @Inject constructor(
    private val repository: AmazonRepository
) : ViewModel(){
    val bannerLiveData: LiveData<List<BannerListItem>> = repository.bannerItems

    fun refresh() {
        fetchBannerItems()
    }

    private fun fetchBannerItems() {
        viewModelScope.launch {
            repository.getBannerImages()
        }
    }

}
