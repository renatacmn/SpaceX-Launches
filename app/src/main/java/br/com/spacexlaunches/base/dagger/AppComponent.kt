package br.com.spacexlaunches.base.dagger

import android.content.Context
import br.com.spacexlaunches.base.application.SpaceXLaunchesApplication
import br.com.spacexlaunches.base.dagger.modules.ActivityBindings
import br.com.spacexlaunches.base.dagger.modules.AppModule
import br.com.spacexlaunches.base.dagger.modules.InterfaceBindings
import br.com.spacexlaunches.base.dagger.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ActivityBindings::class,
        InterfaceBindings::class
    ]
)
interface AppComponent : AndroidInjector<SpaceXLaunchesApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

}
