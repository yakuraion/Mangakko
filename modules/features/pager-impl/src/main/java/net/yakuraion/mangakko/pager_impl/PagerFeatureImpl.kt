package net.yakuraion.mangakko.pager_impl

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.pager.PagerFeature
import net.yakuraion.mangakko.pager_impl.ui.pager.view.PagerFragment
import javax.inject.Inject

class PagerFeatureImpl @Inject constructor() : PagerFeature {

    override fun getPagerFragment(): Fragment {
        return PagerFragment.createFragment()
    }
}
