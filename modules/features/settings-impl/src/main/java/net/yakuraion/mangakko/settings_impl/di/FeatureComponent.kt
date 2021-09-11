package net.yakuraion.mangakko.settings_impl.di

import dagger.Component
import net.yakuraion.mangakko.core_di.app_provider.AppProvider
import net.yakuraion.mangakko.settings_impl.di.viewmodel.ViewModelFactoriesModule
import net.yakuraion.mangakko.settings_impl.ui.settings.view.SettingsFragment

@Component(
    dependencies = [AppProvider::class],
    modules = [ViewModelFactoriesModule::class]
)
interface FeatureComponent {

    fun inject(fragment: SettingsFragment)

    companion object {

        fun create(appProvider: AppProvider): FeatureComponent {
            return DaggerFeatureComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}
