package net.yakuraion.mangakko.settings_impl

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.settings.SettingsFeature
import net.yakuraion.mangakko.settings_impl.ui.settings.view.SettingsFragment
import javax.inject.Inject

class SettingsFeatureImpl @Inject constructor() : SettingsFeature {

    override fun getSettingsFragment(): Fragment {
        return SettingsFragment.createFragment()
    }
}
