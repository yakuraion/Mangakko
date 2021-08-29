package net.yakuraion.mangakko.media_impl

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.media.MediaFeature
import net.yakuraion.mangakko.media_impl.ui.media.view.MediaFragment
import net.yakuraion.mangakko.media_impl.ui.media_overview.view.MediaOverviewFragment
import javax.inject.Inject

class MediaFeatureImpl @Inject constructor() : MediaFeature {

    override fun getMediaFragment(): Fragment {
        return MediaFragment.createFragment()
    }

    override fun getMediaOverviewFragment(): Fragment {
        return MediaOverviewFragment.createFragment()
    }
}
