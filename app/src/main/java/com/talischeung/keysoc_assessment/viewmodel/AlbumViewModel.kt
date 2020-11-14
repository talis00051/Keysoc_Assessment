package com.talischeung.keysoc_assessment.viewmodel

import androidx.lifecycle.MutableLiveData
import com.talischeung.keysoc_assessment.model.Album

class AlbumViewModel: BaseViewModel() {
    val collectionId = MutableLiveData<Int>()
    val collectionName = MutableLiveData<String>()
    val collectionPrice = MutableLiveData<String>()
    val collectionViewUrl = MutableLiveData<String>()

    val artworkUrl100 = MutableLiveData<String>()
    val artworkUrl60 = MutableLiveData<String>()

    val artistName = MutableLiveData<String>()

    fun bind(album: Album) {
        collectionId.value = album.collectionId
        collectionName.value = album.collectionName
        collectionPrice.value = "\$$album.collectionPrice"
        artworkUrl100.value = album.artworkUrl100
        artworkUrl60.value = album.artworkUrl60
        artistName.value = album.artistName
    }
}