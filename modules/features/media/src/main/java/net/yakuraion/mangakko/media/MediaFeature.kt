package net.yakuraion.mangakko.media

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.core_entity.Media

interface MediaFeature {

    fun getMediaFragment(): Fragment

    fun getMediaDetailsFragment(mediaId: Int): Fragment

    fun getMediaDetailsFragment(media: Media): Fragment

    interface Owner {

        fun onMediaMediaChosen(media: Media)
    }
}
