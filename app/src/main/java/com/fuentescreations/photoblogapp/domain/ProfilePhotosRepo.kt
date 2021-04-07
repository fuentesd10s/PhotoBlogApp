package com.fuentescreations.photoblogapp.domain

import com.fuentescreations.photoblogapp.application.ResultState
import com.fuentescreations.photoblogapp.data.models.ProfilePhotos

interface ProfilePhotosRepo {
    suspend fun getProfilesPhotos() : ResultState<List<ProfilePhotos>>
}