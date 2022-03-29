package com.sample.assignmentapp

import android.app.Activity
import com.sample.assignmentapp.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class RestaurantsApp: DaggerApplication(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }


}