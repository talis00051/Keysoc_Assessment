package com.talischeung.keysoc_assessment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.talischeung.keysoc_assessment.R
import com.talischeung.keysoc_assessment.fragment.AlbumsFragment
import com.talischeung.keysoc_assessment.fragment.FavouritesFragment
import com.talischeung.keysoc_assessment.inTransaction

class MainActivity : BaseActivity() {

    companion object {
        private const val FRAGMENT_ID = R.id.fragment_container
    }

    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(AlbumsFragment.newInstance())
    }

    fun <F> replaceFragment(fragment: F) where F : Fragment {
        supportFragmentManager.inTransaction {
            currentFragment = fragment
            replace(FRAGMENT_ID, fragment)
        }
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        for (frag in fm.fragments) {
            if (frag.isVisible) {
                val childFm = frag.childFragmentManager
                if (childFm.backStackEntryCount > 0) {
                    childFm.popBackStack()
                    return
                }
            }
        }
        super.onBackPressed()
    }

    override fun showLoading(): Boolean {
        TODO("Not yet implemented")
    }
}