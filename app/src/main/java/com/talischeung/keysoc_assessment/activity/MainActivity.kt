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

    private fun <F> replaceFragment(fragment: F) where F : Fragment {
        supportFragmentManager.inTransaction {
            currentFragment = fragment
            replace(FRAGMENT_ID, fragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)

        // Set Favourite Button Color
        val favIcon = menu?.findItem(R.id.fav_button)?.icon
        favIcon?.setTint(ContextCompat.getColor(applicationContext, R.color.fav_pink))
        menu?.findItem(R.id.fav_button)?.icon = favIcon

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        when(id) {
            R.id.fav_button -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showLoading(): Boolean {
        TODO("Not yet implemented")
    }
}