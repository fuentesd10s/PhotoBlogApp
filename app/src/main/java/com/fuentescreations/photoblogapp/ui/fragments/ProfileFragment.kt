package com.fuentescreations.photoblogapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.fuentescreations.photoblogapp.R
import com.fuentescreations.photoblogapp.adapters.ProfilePhotosAdapter
import com.fuentescreations.photoblogapp.application.BaseFragment
import com.fuentescreations.photoblogapp.application.ResultState
import com.fuentescreations.photoblogapp.data.models.Photos
import com.fuentescreations.photoblogapp.data.remote.PhotosDataSource
import com.fuentescreations.photoblogapp.databinding.FragmentProfileBinding
import com.fuentescreations.photoblogapp.domain.PhotosRepoImpl
import com.fuentescreations.photoblogapp.viewmodels.ProfilePhotosViewModel
import com.fuentescreations.photoblogapp.viewmodels.PhotosViewModelFactory

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    
    private val profilePhotosViewModel by viewModels<ProfilePhotosViewModel> {
        PhotosViewModelFactory(
            PhotosRepoImpl(
                PhotosDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentProfileBinding.bind(view)

        setupProfilePhotos()
    }

    private fun setupProfilePhotos(){

        binding.refreshLayout.setOnRefreshListener { profilePhotosViewModel.refreshData() }

        val profilePhotosList = mutableListOf<Photos>()
        val adapter=ProfilePhotosAdapter(profilePhotosList)
        binding.rvPhotos.adapter=adapter

        profilePhotosViewModel.getPhotos.observe(viewLifecycleOwner, {
            when (it) {
                is ResultState.Loading -> {
                    onShowLoading()
                }
                is ResultState.Success -> {
                    onSuccessLoading()

                    val lastPhoto=it.data.last()

                    binding.tvUsername.text = lastPhoto.author
                    Glide.with(requireContext()).load(lastPhoto.imageUrl).circleCrop().into(binding.ivPhotoProfile)

                    profilePhotosList.clear()
                    profilePhotosList.addAll(it.data)
                    adapter.notifyDataSetChanged()
                }
                is ResultState.Failure -> {
                    onFailureLoading(it.exception.toString())
                }
            }
        })
    }

    private fun onShowLoading(){
        mSnackbar(binding.root,"")

        binding.refreshLayout.isRefreshing=true
    }
    private fun onSuccessLoading(){
        mSnackbar(binding.root,"")

        binding.refreshLayout.isRefreshing=false
        binding.successLayout.visibility=View.VISIBLE
    }
    private fun onFailureLoading(error:String){
        binding.refreshLayout.isRefreshing=false

        mSnackbar(binding.root,"Something went wrong! Please swipe down to refresh")

        Log.d("Error", "setupProfilePhotos: $error")
    }
}