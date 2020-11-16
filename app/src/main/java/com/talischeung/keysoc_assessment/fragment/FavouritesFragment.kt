package com.talischeung.keysoc_assessment.fragment

import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.talischeung.keysoc_assessment.R
import com.talischeung.keysoc_assessment.activity.MainActivity
import com.talischeung.keysoc_assessment.databinding.FragmentFavouriteBinding
import com.talischeung.keysoc_assessment.handler.FavouriteHandler
import com.talischeung.keysoc_assessment.util.FavouriteManager
import com.talischeung.keysoc_assessment.util.setFavouriteTint
import com.talischeung.keysoc_assessment.viewmodel.FavouritesViewModel

class FavouritesFragment: BaseFragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var favouritesViewModel: FavouritesViewModel

    companion object {
        fun newInstance() = FavouritesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        favouritesViewModel = ViewModelProvider(this).get(FavouritesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAlbums.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        favouritesViewModel.setupHandler(object: FavouriteHandler {
            override fun onClickFavourite(view: View, collectionId: String) {
                if (FavouriteManager.getFavourites().contains(collectionId)) {
                    FavouriteManager.deleteFavourite(collectionId)
                    (view as ImageButton).setFavouriteTint(false)
                } else {
                    FavouriteManager.addFavourite(collectionId)
                    (view as ImageButton).setFavouriteTint(true)
                }
            }
        })

        binding.favouritesViewModel = favouritesViewModel
        favouritesViewModel.loadFavouriteAlbums()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_favourite, menu)

        // Set Menu Button Color
        val albumIcon = menu.findItem(R.id.album_button)?.icon
        albumIcon?.setTint(ContextCompat.getColor(context!!, R.color.text_white))
        menu.findItem(R.id.album_button)?.icon = albumIcon

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.album_button -> {
                (activity as MainActivity).replaceFragment(AlbumsFragment.newInstance())
            }
        }

        return super.onOptionsItemSelected(item)
    }
}