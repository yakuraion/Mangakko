package net.yakuraion.mangakko.favorites

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.core_entity.Media

interface FavoritesFeature {

    fun getFavoritesFragment(): Fragment

    interface Owner {

        fun onFavoritesMediaClick(media: Media)
    }
}
