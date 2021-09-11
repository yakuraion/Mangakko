@file:Suppress("MaxLineLength")

package net.yakuraion.mangakko.settings_impl.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.di.viewmodel.ViewModelKey
import net.yakuraion.mangakko.settings_impl.ui.settings.viewmodel.SettingsViewModel

@Module
interface ViewModelFactoriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindsSettings(impl: SettingsViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}
