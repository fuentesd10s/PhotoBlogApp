package com.fuentescreations.photoblogapp.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.fuentescreations.photoblogapp.data.models.Photos
import com.fuentescreations.photoblogapp.databinding.ItemFeedPhotosBinding

class FeedPhotosAdapter(private val photosList: List<Photos>) : RecyclerView.Adapter<FeedPhotosAdapter.FeedPhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedPhotosViewHolder {
        val binding= ItemFeedPhotosBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        val holder= FeedPhotosViewHolder(binding)

        return holder
    }

    override fun onBindViewHolder(holder: FeedPhotosViewHolder, position: Int) { holder.bind(photosList[position]) }

    override fun getItemCount(): Int = photosList.size

    inner class FeedPhotosViewHolder(private val binding: ItemFeedPhotosBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(feedPhoto: Photos) {

            binding.tvUsername.text = feedPhoto.author

            Glide.with(binding.root.context)
                .load(feedPhoto.imageUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBarProfilePhoto.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBarProfilePhoto.visibility = View.GONE
                        return false
                    }

                })
                .circleCrop()
                .into(binding.ivProfilePhoto)

            Glide.with(binding.root.context)
                .load(feedPhoto.imageUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBarFeedPhoto.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBarFeedPhoto.visibility = View.GONE
                        return false
                    }

                })
                .into(binding.ivFeedPhoto)
        }
    }
}