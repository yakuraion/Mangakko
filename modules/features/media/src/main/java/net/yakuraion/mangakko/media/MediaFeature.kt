package net.yakuraion.mangakko.media

import androidx.fragment.app.Fragment

interface MediaFeature {

    fun getMediaFragment(genre: String): Fragment

    fun getMediaOverviewFragment(): Fragment
}
