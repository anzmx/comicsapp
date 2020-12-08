package com.zeltixdev.comicapp.ui.comic.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zeltixdev.comicapp.databinding.ComicListItemBinding
import com.zeltixdev.comicapp.entity.Comic
import com.zeltixdev.comicapp.ui.comic.list.adapter.viewholder.ComicViewHolder

class ComicListAdapter(
    private val onComicIdClick: (String) -> Unit
) : RecyclerView.Adapter<ComicViewHolder>() {

    var items: List<Comic> = ArrayList(0)
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ComicViewHolder(
            ComicListItemBinding.inflate(inflater, parent, false),
            onComicIdClick
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}