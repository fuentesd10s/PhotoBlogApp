package com.fuentescreations.photoblogapp.viewmodels

import androidx.lifecycle.*
import com.fuentescreations.photoblogapp.application.ResultState
import com.fuentescreations.photoblogapp.data.models.Photos
import com.fuentescreations.photoblogapp.domain.PhotosRepo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class ProfilePhotosViewModel(private val repo:PhotosRepo) : ViewModel(){

    private val loadTrigger = MutableLiveData(Unit)

    fun refreshData() {
        loadTrigger.value = Unit
    }

    val getPhotos: LiveData<ResultState<List<Photos>>> = loadTrigger.switchMap {

        liveData(Dispatchers.IO) {

            emit(ResultState.Loading())

            try {
                emit(repo.getProfilesPhotos())
            } catch (e: Exception) {
                emit(ResultState.Failure(e))
            }
        }
    }
}

class PhotosViewModelFactory(private val repo:PhotosRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfilePhotosViewModel(repo) as T
    }
}