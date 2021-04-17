package com.fuentescreations.photoblogapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.fuentescreations.photoblogapp.R
import com.fuentescreations.photoblogapp.adapters.FeedPhotosAdapter
import com.fuentescreations.photoblogapp.adapters.ProfilePhotosAdapter
import com.fuentescreations.photoblogapp.application.BaseFragment
import com.fuentescreations.photoblogapp.application.ResultState
import com.fuentescreations.photoblogapp.data.models.Photos
import com.fuentescreations.photoblogapp.data.remote.PhotosDataSource
import com.fuentescreations.photoblogapp.databinding.FragmentFeedBinding
import com.fuentescreations.photoblogapp.domain.PhotosRepoImpl
import com.fuentescreations.photoblogapp.viewmodels.PhotosViewModelFactory
import com.fuentescreations.photoblogapp.viewmodels.ProfilePhotosViewModel

class FeedFragment : BaseFragment(R.layout.fragment_feed){

    private lateinit var binding: FragmentFeedBinding

    private val feedPhotosViewModel by viewModels<ProfilePhotosViewModel> {
        PhotosViewModelFactory(
            PhotosRepoImpl(
                PhotosDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentFeedBinding.bind(view)

        setupFeed()
    }

    private fun setupFeed() {
        binding.refreshLayout.setOnRefreshListener { feedPhotosViewModel.refreshData() }

        val feedPhotosList = mutableListOf<Photos>()
        val adapter= FeedPhotosAdapter(feedPhotosList)
        binding.rvFeed.adapter=adapter

        feedPhotosViewModel.getPhotos.observe(viewLifecycleOwner, {
            when (it) {
                is ResultState.Loading -> {
                    onShowLoading()
                }
                is ResultState.Success -> {
                    onSuccessLoading()

                    feedPhotosList.clear()
                    feedPhotosList.addAll(it.data.take(10))
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
        binding.rvFeed.visibility=View.VISIBLE
    }
    private fun onFailureLoading(error:String){
        binding.refreshLayout.isRefreshing=false

        mSnackbar(binding.root,"Something went wrong! Please swipe down to refresh")

        Log.d("Error", "setupFeedPhotos: $error")
    }
}