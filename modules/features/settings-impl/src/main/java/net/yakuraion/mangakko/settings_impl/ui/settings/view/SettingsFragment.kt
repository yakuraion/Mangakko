package net.yakuraion.mangakko.settings_impl.ui.settings.view

import android.content.Context
import android.os.Bundle
import android.view.View
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.settings_impl.R
import net.yakuraion.mangakko.settings_impl.di.injector
import net.yakuraion.mangakko.settings_impl.ui.settings.viewmodel.SettingsViewModel
import javax.inject.Inject

@Suppress("EXPERIMENTAL_API_USAGE")
class SettingsFragment : BaseFragment<SettingsViewModel>(
    SettingsViewModel::class,
    R.layout.settings_fragment_settings
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        fun createFragment(): SettingsFragment {
            return SettingsFragment()
        }
    }
}
