package com.talischeung.keysoc_assessment.viewmodel

import com.talischeung.keysoc_assessment.server.retrofit.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(albumsViewModel: AlbumsViewModel)
    fun inject(favouritesViewModel: FavouritesViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        
        fun networkModule(networkModule: NetworkModule) : Builder
    }
}