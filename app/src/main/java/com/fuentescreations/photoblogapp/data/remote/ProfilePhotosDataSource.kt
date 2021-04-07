package com.fuentescreations.photoblogapp.data.remote

import com.fuentescreations.photoblogapp.application.AppConstans
import com.fuentescreations.photoblogapp.application.ResultState
import com.fuentescreations.photoblogapp.data.api.APIService
import com.fuentescreations.photoblogapp.data.models.ProfilePhotos
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfilePhotosDataSource {
    suspend fun getProfilesPhotos(): ResultState<List<ProfilePhotos>> {

        val profilePhotosList = mutableListOf<ProfilePhotos>()

        val mRetrofit = Retrofit
            .Builder()
            .baseUrl(AppConstans.BASE_URL_PROFILE_PHOTOS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)

        profilePhotosList.addAll(mRetrofit.getProfilePhotos(AppConstans.LIST_PROFILE_PHOTOS_PAGE+(0..10).random().toString()))

        return ResultState.Success(profilePhotosList)
    }
}