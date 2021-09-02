package net.yakuraion.mangakko.pager

import androidx.fragment.app.Fragment

interface PagerFeature {

    fun getPagerFragment(): Fragment

    interface Owner {

        fun onPagerMediaChosen(mediaId: Int)
    }
}
