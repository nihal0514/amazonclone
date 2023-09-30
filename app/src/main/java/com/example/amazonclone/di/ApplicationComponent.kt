package com.example.amazonclone.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.amazonclone.fragments.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class,ViewModelModule::class])
interface ApplicationComponent {
    fun inject(homeFragment: HomeFragment)

    fun getMap(): Map<Class<*>, ViewModel>
    // this is the map function that reruns any map data that in the Dagger reach

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

}