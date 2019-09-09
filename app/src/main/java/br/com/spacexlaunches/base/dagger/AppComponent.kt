package br.com.spacexlaunches.base.dagger

import android.app.Application
import br.com.spacexlaunches.base.application.SpaceXLaunchesApplication
import br.com.spacexlaunches.base.dagger.modules.ActivityBindings
import br.com.spacexlaunches.base.dagger.modules.AppModule
import br.com.spacexlaunches.base.dagger.modules.FragmentBindings
import br.com.spacexlaunches.base.dagger.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AppModule::class),
        (NetworkModule::class),
        (ActivityBindings::class),
        (FragmentBindings::class)
    ]
)
interface AppComponent : AndroidInjector<SpaceXLaunchesApplication> {

    fun inject(application: Application)

}
