package br.com.spacexlaunches.base.dagger.modules

import br.com.spacexlaunches.base.BaseActivity
import br.com.spacexlaunches.detail.DetailActivity
import br.com.spacexlaunches.list.ListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindings {

    @ContributesAndroidInjector
    abstract fun contributesBaseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun contributesListActivity(): ListActivity

    @ContributesAndroidInjector
    abstract fun contributesDetailActivity(): DetailActivity

}
