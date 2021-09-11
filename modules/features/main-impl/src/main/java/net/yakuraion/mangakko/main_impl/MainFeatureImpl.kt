package net.yakuraion.mangakko.main_impl

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.main.MainFeature
import net.yakuraion.mangakko.main_impl.ui.main.view.MainFragment
import javax.inject.Inject

class MainFeatureImpl @Inject constructor() : MainFeature {

    override fun getMainFragment(): Fragment {
        return MainFragment.createFragment()
    }
}
