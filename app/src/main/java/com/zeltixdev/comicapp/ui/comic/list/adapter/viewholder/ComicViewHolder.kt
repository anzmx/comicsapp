package com.zeltixdev.comicapp.ui.comic.list.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zeltixdev.comicapp.databinding.ComicListItemBinding
import com.zeltixdev.comicapp.entity.Comic
import com.zeltixdev.comicapp.entity.url

private const val STROKE = 5f
private const val CENTER_RADIUS = 30f

class ComicViewHolder(
    private val binding: ComicListItemBinding,
    onComicIdClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var comicId: String
    private val circularProgressDrawable: CircularProgressDrawable

    init {
        binding.root.setOnClickListener {
            onComicIdClick(comicId)
        }
        circularProgressDrawable = CircularProgressDrawable(binding.root.context).apply {
            strokeWidth = STROKE
            centerRadius = CENTER_RADIUS
            start()
        }
    }

    fun bindItem(comic: Comic) {
        comicId = comic.id.toString()
        binding.tvName.text = comic.title
        Glide.with(binding.ivImage)
            .load(comic.thumbnail.url())
            .placeholder(circularProgressDrawable)
            .diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().centerCrop()
            .into(binding.ivImage)
    }
}