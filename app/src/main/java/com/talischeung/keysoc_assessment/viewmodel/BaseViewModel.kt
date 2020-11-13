package com.talischeung.keysoc_assessment.viewmodel

import androidx.lifecycle.ViewModel
import com.talischeung.keysoc_assessment.server.retrofit.NetworkModule

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is AlbumsViewModel -> injector.inject(this)
        }
    }
}