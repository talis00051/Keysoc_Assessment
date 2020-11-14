package com.talischeung.keysoc_assessment.viewmodel

import androidx.lifecycle.MutableLiveData
import com.talischeung.keysoc_assessment.model.Album

class AlbumViewModel: BaseViewModel() {
    val collectionId = MutableLiveData<Int>()
    val collectionName = MutableLiveData<String>()
    val collectionPrice = MutableLiveData<Double>()

    fun bind(album: Album) {
        collectionId.value = album.collectionId
        collectionName.value = album.collectionName
    }
}