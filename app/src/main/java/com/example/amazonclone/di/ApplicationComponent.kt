package com.example.amazonclone.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.amazonclone.fragments.HomeFragment
import com.example.amazonclone.ui.product.Prodpage
import com.example.amazonclone.ui.product.ProductDetails
import com.example.amazonclone.ui.signin.LoginFragment
import com.example.amazonclone.ui.signin.SignUpFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class,ViewModelModule::class])
interface ApplicationComponent {
    fun inject(homeFragment: HomeFragment)

    fun inject1(signUpFragment: SignUpFragment)

    fun inject1(loginFragment: LoginFragment)

    fun inject2(prodpage: Prodpage)

    fun inject3(productDetails: ProductDetails)


    fun getMap(): Map<Class<*>, ViewModel>
    // this is the map function that reruns any map data that in the Dagger reach

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

}