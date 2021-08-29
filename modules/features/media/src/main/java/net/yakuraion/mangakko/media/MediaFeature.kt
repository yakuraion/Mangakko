package net.yakuraion.mangakko.media

import androidx.fragment.app.Fragment

interface MediaFeature {

    fun getMediaFragment(): Fragment

    fun getMediaOverviewFragment(): Fragment
}
