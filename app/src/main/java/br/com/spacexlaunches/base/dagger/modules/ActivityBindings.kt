package br.com.spacexlaunches.base.dagger.modules

import br.com.spacexlaunches.MainActivity
import br.com.spacexlaunches.base.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindings {

    @ContributesAndroidInjector
    abstract fun contributesBaseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

}
