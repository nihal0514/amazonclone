package com.example.amazonclone

import android.app.Application
import com.example.amazonclone.di.ApplicationComponent
import com.example.amazonclone.di.DaggerApplicationComponent

class AmazonApplication : Application(){
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent= DaggerApplicationComponent.factory().create(this)
    }

}