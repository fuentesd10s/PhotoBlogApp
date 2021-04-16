package com.fuentescreations.photoblogapp.data.remote

import com.fuentescreations.photoblogapp.application.AppConstans
import com.fuentescreations.photoblogapp.application.ResultState
import com.fuentescreations.photoblogapp.data.api.APIService
import com.fuentescreations.photoblogapp.data.models.Photos
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotosDataSource {
    suspend fun getProfilesPhotos(): ResultState<List<Photos>> {

        val profilePhotosList = mutableListOf<Photos>()

        val mRetrofit = Retrofit
            .Builder()
            .baseUrl(AppConstans.BASE_URL_PROFILE_PHOTOS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)

        profilePhotosList.addAll(mRetrofit.profilePhotos(AppConstans.LIST_PROFILE_PHOTOS_PAGE+(0..10).random().toString()))

        return ResultState.Success(profilePhotosList)
    }
}