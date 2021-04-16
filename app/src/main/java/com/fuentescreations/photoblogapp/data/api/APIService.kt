package com.fuentescreations.photoblogapp.data.api

import com.fuentescreations.photoblogapp.data.models.Photos
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun profilePhotos(@Url url:String=""):List<Photos>
}