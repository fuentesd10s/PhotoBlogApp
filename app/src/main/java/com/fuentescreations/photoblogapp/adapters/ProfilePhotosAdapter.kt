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
import com.fuentescreations.photoblogapp.data.models.ProfilePhotos
import com.fuentescreations.photoblogapp.databinding.ItemProfilePhotosBinding

class ProfilePhotosAdapter(private val profilePhotosList:List<ProfilePhotos>) : RecyclerView.Adapter<ProfilePhotosAdapter.ProfilePhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePhotosViewHolder {
        val binding=ItemProfilePhotosBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        val holder=ProfilePhotosViewHolder(binding)

        return holder
    }

    override fun onBindViewHolder(holder: ProfilePhotosViewHolder, position: Int) { holder.bind(profilePhotosList[position]) }

    override fun getItemCount(): Int = profilePhotosList.size

    class ProfilePhotosViewHolder(private val binding: ItemProfilePhotosBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profilePhotos: ProfilePhotos){
            Glide.with(binding.root.context).load(profilePhotos.imageUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                }).into(binding.ivProfilePhoto)
        }
    }
}