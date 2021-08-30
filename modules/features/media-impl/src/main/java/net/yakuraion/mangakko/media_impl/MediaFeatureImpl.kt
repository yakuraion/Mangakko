package net.yakuraion.mangakko.media_impl

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.media.MediaFeature
import net.yakuraion.mangakko.media_impl.ui.media.view.MediaFragment
import javax.inject.Inject

class MediaFeatureImpl @Inject constructor() : MediaFeature {

    override fun getMediaFragment(): Fragment {
        return MediaFragment.createFragment()
    }
}
