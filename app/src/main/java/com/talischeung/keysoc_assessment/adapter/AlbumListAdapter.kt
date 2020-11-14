package com.talischeung.keysoc_assessment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.talischeung.keysoc_assessment.R
import com.talischeung.keysoc_assessment.databinding.ItemAlbumBinding
import com.talischeung.keysoc_assessment.handler.AlbumHandler
import com.talischeung.keysoc_assessment.model.Album
import com.talischeung.keysoc_assessment.viewmodel.AlbumViewModel

class AlbumListAdapter(val onClick: AlbumHandler): RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {
    private var albumList: MutableList<Album> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemAlbumBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_album, parent, false)
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albumList[position])
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    fun updateAlbumList(albumList: List<Album>) {
        this.albumList.clear()
        this.albumList.addAll(albumList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemAlbumBinding, val onClick: AlbumHandler): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = AlbumViewModel()

        fun bind(album: Album) {
            viewModel.bind(album)
            binding.album = viewModel
            binding.handler = onClick
        }
    }
}