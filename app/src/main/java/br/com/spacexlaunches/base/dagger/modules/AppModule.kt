package br.com.spacexlaunches.base.dagger.modules

import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideLocale(): Locale = Locale.UK

}