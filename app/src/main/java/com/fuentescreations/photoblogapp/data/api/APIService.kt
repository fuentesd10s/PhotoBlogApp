package com.fuentescreations.photoblogapp.data.api

import com.fuentescreations.photoblogapp.data.models.ProfilePhotos
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getProfilePhotos(@Url url:String=""):List<ProfilePhotos>
}