package com.fuentescreations.photoblogapp.ui.fragments

import android.os.Bundle
import android.view.View
import com.fuentescreations.photoblogapp.R
import com.fuentescreations.photoblogapp.application.BaseFragment
import com.fuentescreations.photoblogapp.databinding.FragmentFeedBinding

class FeedFragment : BaseFragment(R.layout.fragment_feed){

    private lateinit var binding: FragmentFeedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentFeedBinding.bind(view)
    }
}