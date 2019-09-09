package br.com.spacexlaunches.base.application

import android.app.Activity
import android.app.Application
import br.com.spacexlaunches.base.dagger.DaggerAppComponent
import br.com.spacexlaunches.base.dagger.modules.AppModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class SpaceXLaunchesApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = androidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }

}
