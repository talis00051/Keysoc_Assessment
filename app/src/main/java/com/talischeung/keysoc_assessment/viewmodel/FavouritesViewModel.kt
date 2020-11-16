package com.talischeung.keysoc_assessment.viewmodel

import androidx.lifecycle.MutableLiveData
import com.talischeung.keysoc_assessment.adapter.FavouriteListAdapter
import com.talischeung.keysoc_assessment.afterObserveOn
import com.talischeung.keysoc_assessment.handler.FavouriteHandler
import com.talischeung.keysoc_assessment.model.Album
import com.talischeung.keysoc_assessment.model.Albums
import com.talischeung.keysoc_assessment.util.FavouriteManager
import com.talischeung.keysoc_assessment.util.LogE
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class FavouritesViewModel: BaseViewModel() {
    @Inject
    lateinit var service: com.talischeung.keysoc_assessment.server.Service

    private lateinit var subscription: Disposable

    val showReloadButton = MutableLiveData<Boolean>()

    lateinit var onClick: FavouriteHandler
    lateinit var favouriteListAdapter: FavouriteListAdapter

    init {
        showReloadButton.value = false
    }

    fun loadFavouriteAlbums() {
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

    fun setupHandler(handler: FavouriteHandler) {
        this.onClick = handler
        favouriteListAdapter = FavouriteListAdapter(handler)
    }

    private fun onRetrieveAlbumsSuccess(albums: Albums) {
        val favouriteAlbums = mutableListOf<Album>()
        if (albums.resultCount > 0) {
            showReloadButton.value = false
            val favourites = FavouriteManager.getFavourites()
            for (album in albums.results) {
                if (favourites.contains("$album.collectionId")) {
                    favouriteAlbums.add(album)
                }
            }
            favouriteListAdapter.updateFavouriteAlbumList(favouriteAlbums)
        } else {
            showReloadButton.value = true
        }
    }

    private fun onRetrieveAlbumsError() {
        if (favouriteListAdapter.itemCount == 0) {
            showReloadButton.value = true
        }
    }
}