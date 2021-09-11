package net.yakuraion.mangakko.settings_impl.ui.settings.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import kotlinx.android.synthetic.main.settings_fragment_settings.rootLayout
import kotlinx.android.synthetic.main.settings_fragment_settings.themeDarkRadioButton
import kotlinx.android.synthetic.main.settings_fragment_settings.themeLightRadioButton
import kotlinx.android.synthetic.main.settings_fragment_settings.themeRadioGroup
import kotlinx.android.synthetic.main.settings_fragment_settings.themeSystemRadioButton
import net.yakuraion.mangakko.core_entity.settings.ThemeMode
import net.yakuraion.mangakko.core_entity.settings.ThemeMode.DARK
import net.yakuraion.mangakko.core_entity.settings.ThemeMode.LIGHT
import net.yakuraion.mangakko.core_entity.settings.ThemeMode.SYSTEM
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
        setUpInsets()
        setUpThemeRadioGroup()
        viewModel.apply {
            setThemeModeLiveData.observe(viewLifecycleOwner) { updateThemeRadioSelection(it) }
        }
    }

    private fun setUpInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(rootLayout) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(top = insets.top)
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun setUpThemeRadioGroup() {
        themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.themeSystemRadioButton -> viewModel.onThemeSystemChecked()
                R.id.themeLightRadioButton -> viewModel.onThemeLightChecked()
                R.id.themeDarkRadioButton -> viewModel.onThemeDarkChecked()
            }
        }
    }

    private fun updateThemeRadioSelection(themeMode: ThemeMode) {
        val view = when (themeMode) {
            SYSTEM -> themeSystemRadioButton
            LIGHT -> themeLightRadioButton
            DARK -> themeDarkRadioButton
        }
        view.isChecked = true
    }

    companion object {

        fun createFragment(): SettingsFragment {
            return SettingsFragment()
        }
    }
}
