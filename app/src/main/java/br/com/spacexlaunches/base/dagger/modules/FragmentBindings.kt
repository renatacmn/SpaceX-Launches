package br.com.spacexlaunches.base.dagger.modules

import br.com.spacexlaunches.detail.DetailFragment
import br.com.spacexlaunches.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindings {

    @ContributesAndroidInjector
    abstract fun contributesListFragment(): ListFragment

    @ContributesAndroidInjector
    abstract fun contributesDetailFragment(): DetailFragment

}
