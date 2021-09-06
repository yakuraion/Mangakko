package net.yakuraion.mangakko.pager

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.core_entity.Media

interface PagerFeature {

    fun getPagerFragment(): Fragment

    interface Owner {

        fun onPagerMediaChosen(media: Media)
    }
}
