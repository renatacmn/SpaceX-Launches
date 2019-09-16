package br.com.spacexlaunches.base.dagger.modules

import br.com.spacexlaunches.util.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesGlideImageLoader() = GlideImageLoader()

}
