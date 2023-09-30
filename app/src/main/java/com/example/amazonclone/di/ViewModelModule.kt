package com.example.amazonclone.di

import androidx.lifecycle.ViewModel
import com.example.amazonclone.viewModel.CategoryViewModel
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
}