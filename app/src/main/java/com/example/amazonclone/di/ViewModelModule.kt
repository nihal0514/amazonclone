package com.example.amazonclone.di

import androidx.lifecycle.ViewModel
import com.example.amazonclone.viewModel.BannerViewModel
import com.example.amazonclone.viewModel.CategoryViewModel
import com.example.amazonclone.viewModel.LoginViewModel
import com.example.amazonclone.viewModel.ProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @ClassKey(CategoryViewModel::class)
    @IntoMap
    abstract fun categoryViewModel(categoryViewModel: CategoryViewModel): ViewModel

    @Binds
    @ClassKey(BannerViewModel::class)
    @IntoMap
    abstract fun bannerViewModel(bannerViewModel: BannerViewModel): ViewModel

    @Binds
    @ClassKey(LoginViewModel::class)
    @IntoMap
    abstract fun loginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @ClassKey(ProductViewModel::class)
    @IntoMap
    abstract fun prodViewModel(productViewModel: ProductViewModel): ViewModel
}