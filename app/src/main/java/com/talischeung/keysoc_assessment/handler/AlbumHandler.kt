package com.talischeung.keysoc_assessment.handler

import android.view.View
import com.talischeung.keysoc_assessment.viewmodel.AlbumViewModel

interface AlbumHandler {
    fun onClickAlbum(view: View, album: AlbumViewModel)
    fun onClickFavourite(view: View, collectionId: String)
}