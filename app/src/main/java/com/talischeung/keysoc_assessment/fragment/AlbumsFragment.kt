package com.talischeung.keysoc_assessment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talischeung.keysoc_assessment.R
import com.talischeung.keysoc_assessment.databinding.FragmentAlbumsBinding
import com.talischeung.keysoc_assessment.handler.AlbumHandler
import com.talischeung.keysoc_assessment.util.LogD
import com.talischeung.keysoc_assessment.viewmodel.AlbumViewModel
import com.talischeung.keysoc_assessment.viewmodel.AlbumsViewModel

class AlbumsFragment: BaseFragment() {
    private lateinit var binding: FragmentAlbumsBinding
    private lateinit var albumsViewModel: AlbumsViewModel

    companion object {
        fun newInstance() = AlbumsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumsViewModel = ViewModelProvider(this).get(AlbumsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_albums, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAlbums.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        albumsViewModel.setupHandler(object: AlbumHandler {
            override fun onClickAlbum(view: View, album: AlbumViewModel) {
                LogD(album)
            }

            override fun onClickFavourite(view: View, album: AlbumViewModel) {
                LogD(album)
            }
        })
        binding.albumsViewModel = albumsViewModel
        albumsViewModel.loadAlbums()
    }
}