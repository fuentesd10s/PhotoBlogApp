package com.fuentescreations.photoblogapp.domain

import com.fuentescreations.photoblogapp.application.ResultState
import com.fuentescreations.photoblogapp.data.models.Photos

interface PhotosRepo {
    suspend fun getProfilesPhotos() : ResultState<List<Photos>>
}