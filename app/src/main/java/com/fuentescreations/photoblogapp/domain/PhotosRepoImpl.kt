package com.fuentescreations.photoblogapp.domain

import com.fuentescreations.photoblogapp.application.ResultState
import com.fuentescreations.photoblogapp.data.models.Photos
import com.fuentescreations.photoblogapp.data.remote.PhotosDataSource

class PhotosRepoImpl(private val repo:PhotosDataSource) : PhotosRepo {
    override suspend fun getProfilesPhotos(): ResultState<List<Photos>> {
        return repo.getProfilesPhotos()
    }
}