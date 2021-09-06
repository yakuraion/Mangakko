package net.yakuraion.mangakko.media_impl

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.media.MediaFeature
import net.yakuraion.mangakko.media_impl.ui.media.view.MediaFragment
import net.yakuraion.mangakko.media_impl.ui.media_details.view.MediaDetailsFragment
import javax.inject.Inject

class MediaFeatureImpl @Inject constructor() : MediaFeature {

    override fun getMediaFragment(): Fragment {
        return MediaFragment.createFragment()
    }

    override fun getMediaDetailsFragment(mediaId: Int): Fragment {
        return MediaDetailsFragment.createFragment(mediaId)
    }

    override fun getMediaDetailsFragment(media: Media): Fragment {
        return MediaDetailsFragment.createFragment(media)
    }
}
