package com.fuentescreations.photoblogapp.viewmodels

import androidx.lifecycle.*
import com.fuentescreations.photoblogapp.application.ResultState
import com.fuentescreations.photoblogapp.data.models.ProfilePhotos
import com.fuentescreations.photoblogapp.domain.ProfilePhotosRepo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class ProfilePhotosViewModel(private val repo:ProfilePhotosRepo) : ViewModel(){

    private val loadTrigger = MutableLiveData(Unit)

    fun refreshData() {
        loadTrigger.value = Unit
    }

    val getProfilePhotos: LiveData<ResultState<List<ProfilePhotos>>> = loadTrigger.switchMap {

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

class ProfilePhotosViewModelFactory(private val repo:ProfilePhotosRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfilePhotosViewModel(repo) as T
    }
}