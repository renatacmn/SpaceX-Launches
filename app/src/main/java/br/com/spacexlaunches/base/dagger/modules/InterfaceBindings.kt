package br.com.spacexlaunches.base.dagger.modules

import br.com.spacexlaunches.util.GlideImageLoader
import br.com.spacexlaunches.util.ImageLoader
import dagger.Binds
import dagger.Module

@Module
abstract class InterfaceBindings {

    @Binds
    abstract fun bindImageLoader(imageLoader: GlideImageLoader): ImageLoader

}
