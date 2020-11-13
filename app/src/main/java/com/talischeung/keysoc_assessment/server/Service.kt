package com.talischeung.keysoc_assessment.server

import com.talischeung.keysoc_assessment.model.Albums
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("search")
    fun getAlbums(@Query("term") term: String, @Query("entity") entity: String): Observable<Albums>
}