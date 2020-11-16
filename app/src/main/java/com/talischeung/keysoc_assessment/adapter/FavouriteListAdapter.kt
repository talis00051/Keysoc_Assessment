package com.talischeung.keysoc_assessment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.talischeung.keysoc_assessment.R
import com.talischeung.keysoc_assessment.databinding.ItemFavouriteAlbumBinding
import com.talischeung.keysoc_assessment.handler.FavouriteHandler
import com.talischeung.keysoc_assessment.model.Album
import com.talischeung.keysoc_assessment.viewmodel.AlbumViewModel

class FavouriteListAdapter(val onClick: FavouriteHandler): RecyclerView.Adapter<FavouriteListAdapter.ViewHolder>() {
    private var favouriteAlbumList: MutableList<Album> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemFavouriteAlbumBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_favourite_album, parent, false)
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favouriteAlbumList[position])
    }

    override fun getItemCount(): Int {
        return favouriteAlbumList.size
    }

    fun updateFavouriteAlbumList(albumList: List<Album>) {
        this.favouriteAlbumList.clear()
        this.favouriteAlbumList.addAll(albumList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemFavouriteAlbumBinding, val onClick: FavouriteHandler): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = AlbumViewModel()

        fun bind(album: Album) {
            viewModel.bind(album)
            binding.album = viewModel
            binding.handler = onClick
        }
    }
}