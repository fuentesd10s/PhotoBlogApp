package com.fuentescreations.photoblogapp.domain

import com.fuentescreations.photoblogapp.application.ResultState
import com.fuentescreations.photoblogapp.data.models.ProfilePhotos
import com.fuentescreations.photoblogapp.data.remote.ProfilePhotosDataSource

class ProfilePhotosRepoImpl(private val repo:ProfilePhotosDataSource) : ProfilePhotosRepo {
    override suspend fun getProfilesPhotos(): ResultState<List<ProfilePhotos>> {
        return repo.getProfilesPhotos()
    }
}