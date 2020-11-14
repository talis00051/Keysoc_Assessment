package com.talischeung.keysoc_assessment.viewmodel

import androidx.lifecycle.MutableLiveData
import com.talischeung.keysoc_assessment.adapter.AlbumListAdapter
import com.talischeung.keysoc_assessment.afterObserveOn
import com.talischeung.keysoc_assessment.handler.AlbumHandler
import com.talischeung.keysoc_assessment.model.Albums
import com.talischeung.keysoc_assessment.server.Service
import com.talischeung.keysoc_assessment.util.LogE
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AlbumsViewModel: BaseViewModel() {
    @Inject
    lateinit var service: Service

    private lateinit var subscription: Disposable

    val showReloadButton = MutableLiveData<Boolean>()

    lateinit var onClick: AlbumHandler
    lateinit var albumListAdapter: AlbumListAdapter

    init {
        showReloadButton.value = false
    }

    fun loadAlbums() {
        subscription = service.getAlbums("jack+johnson", "album")
                .afterObserveOn {
                    doOnSubscribe {

                    }.subscribe(
                            { result ->
                                onRetrieveAlbumsSuccess(result)
                            },
                            {error ->
                                LogE(error)
                                onRetrieveAlbumsError()
                            }
                    )
                }
    }

    fun setupHandler(handler: AlbumHandler) {
        this.onClick = handler
        albumListAdapter = AlbumListAdapter(handler)
    }

    private fun onRetrieveAlbumsSuccess(albums: Albums) {
        if (albums.resultCount > 0) {
            showReloadButton.value = false
            albumListAdapter.updateAlbumList(albums.results)
        } else {
            showReloadButton.value = true
        }
    }

    private fun onRetrieveAlbumsError() {
        if (albumListAdapter.itemCount == 0) {
            showReloadButton.value = true
        }
    }
}