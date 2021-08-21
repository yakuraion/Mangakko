package net.yakuraion.mangakko.main_impl.di

import dagger.Component
import net.yakuraion.mangakko.core_di.app_provider.AppProvider
import net.yakuraion.mangakko.main_impl.di.viewmodel.ViewModelFactoriesModule
import net.yakuraion.mangakko.main_impl.ui.main.view.MainFragment

@Component(
    dependencies = [AppProvider::class],
    modules = [ViewModelFactoriesModule::class]
)
interface FeatureComponent {

    fun inject(fragment: MainFragment)

    companion object {

        fun create(appProvider: AppProvider): FeatureComponent {
            return DaggerFeatureComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}
