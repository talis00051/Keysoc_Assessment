package com.talischeung.keysoc_assessment.util

import com.talischeung.keysoc_assessment.DemoApplication

class FavouriteManager {

    companion object {
        private const val FAV_SHARED_PREF_ID = "favourites"

        fun getFavourites(): Set<String> {
            val favourites = DemoApplication.sharedPreferences.getStringSet(FAV_SHARED_PREF_ID, setOf()) ?: setOf()
            LogD(favourites)
            return favourites
        }

        fun addFavourite(collectionId: String) {
            var favourites = getFavourites()
            LogD("ADD FAV: $collectionId")
            DemoApplication.sharedPreferences.edit().apply {
                favourites = favourites.plus(collectionId)
                putStringSet(FAV_SHARED_PREF_ID, favourites)
                apply()
            }
        }

        fun deleteFavourite(collectionId: String) {
            var favourites = getFavourites()
            LogD("DELETE FAV: $collectionId")
            if (favourites.contains(collectionId)) {
                DemoApplication.sharedPreferences.edit().apply {
                    favourites = favourites.minus(collectionId)
                    putStringSet(FAV_SHARED_PREF_ID, favourites)
                    apply()
                }
            }
        }
    }
}

