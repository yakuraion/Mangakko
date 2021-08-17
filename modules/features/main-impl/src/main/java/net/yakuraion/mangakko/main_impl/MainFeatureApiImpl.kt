package net.yakuraion.mangakko.main_impl

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.MainFeatureApi
import net.yakuraion.mangakko.main_impl.ui.main.view.MainFragment
import javax.inject.Inject

class MainFeatureApiImpl @Inject constructor() : MainFeatureApi {

    override fun getMainFragment(): Fragment {
        return MainFragment.createFragment()
    }
}
