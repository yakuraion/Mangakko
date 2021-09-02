package net.yakuraion.mangakko.media

import androidx.fragment.app.Fragment

interface MediaFeature {

    fun getMediaFragment(): Fragment

    fun getMediaDetailsFragment(mediaId: Int): Fragment

    interface Owner {

        fun onMediaMediaChosen(mediaId: Int)
    }
}
