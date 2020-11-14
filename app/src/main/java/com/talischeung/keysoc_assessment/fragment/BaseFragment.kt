package com.talischeung.keysoc_assessment.fragment

import androidx.fragment.app.Fragment
import com.talischeung.keysoc_assessment.inTransaction

open class BaseFragment: Fragment() {

    val rootFragment: BaseFragment
        get() {
            return if (parentFragment != null){
                val parent: BaseFragment? = parentFragment as? BaseFragment
                parent?.rootFragment ?: this
            }else{
                this
            }
        }

    fun <F> addFragmentToStack(fragmentId: Int, fragment: F) where F : Fragment {
        for (fm in rootFragment.childFragmentManager.fragments) {
            if (fm::class == fragment::class) {
                return
            }
        }
        rootFragment.childFragmentManager.inTransaction {
            addToBackStack(fragment::class.java.simpleName).add(fragmentId, fragment, fragment::class.java.simpleName)
        }
    }
}