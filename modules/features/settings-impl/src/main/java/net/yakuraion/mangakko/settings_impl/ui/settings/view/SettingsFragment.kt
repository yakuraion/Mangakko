package net.yakuraion.mangakko.settings_impl.ui.settings.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import kotlinx.android.synthetic.main.settings_fragment_settings.rootLayout
import kotlinx.android.synthetic.main.settings_fragment_settings_media_types.mediaTypesAnimeAndMangaRadioButton
import kotlinx.android.synthetic.main.settings_fragment_settings_media_types.mediaTypesAnimeRadioButton
import kotlinx.android.synthetic.main.settings_fragment_settings_media_types.mediaTypesMangaRadioButton
import kotlinx.android.synthetic.main.settings_fragment_settings_media_types.mediaTypesRadioGroup
import kotlinx.android.synthetic.main.settings_fragment_settings_theme_mode.themeDarkRadioButton
import kotlinx.android.synthetic.main.settings_fragment_settings_theme_mode.themeLightRadioButton
import kotlinx.android.synthetic.main.settings_fragment_settings_theme_mode.themeRadioGroup
import kotlinx.android.synthetic.main.settings_fragment_settings_theme_mode.themeSystemRadioButton
import net.yakuraion.mangakko.core_entity.settings.MediaTypesToShow
import net.yakuraion.mangakko.core_entity.settings.MediaTypesToShow.ANIME_AND_MANGA
import net.yakuraion.mangakko.core_entity.settings.MediaTypesToShow.ONLY_ANIME
import net.yakuraion.mangakko.core_entity.settings.MediaTypesToShow.ONLY_MANGA
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
        setUpMediaTypesRadioGroup()
        viewModel.apply {
            setThemeModeLiveData.observe(viewLifecycleOwner) { updateThemeRadioSelection(it) }
            setMediaTypesLiveData.observe(viewLifecycleOwner) { updateMediaTypesRadioSelection(it) }
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

    private fun setUpMediaTypesRadioGroup() {
        mediaTypesRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.mediaTypesAnimeAndMangaRadioButton -> viewModel.onMediaTypesAnimeAndMangaChecked()
                R.id.mediaTypesAnimeRadioButton -> viewModel.onMediaTypesAnimeChecked()
                R.id.mediaTypesMangaRadioButton -> viewModel.onMediaTypesMangaChecked()
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

    private fun updateMediaTypesRadioSelection(mediaTypes: MediaTypesToShow) {
        val view = when (mediaTypes) {
            ANIME_AND_MANGA -> mediaTypesAnimeAndMangaRadioButton
            ONLY_ANIME -> mediaTypesAnimeRadioButton
            ONLY_MANGA -> mediaTypesMangaRadioButton
        }
        view.isChecked = true
    }

    companion object {

        fun createFragment(): SettingsFragment {
            return SettingsFragment()
        }
    }
}
