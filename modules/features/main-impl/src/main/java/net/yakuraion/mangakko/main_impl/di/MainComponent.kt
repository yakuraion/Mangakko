package net.yakuraion.mangakko.main_impl.di

import dagger.Component
import net.yakuraion.mangakko.core_di.app_provider.AppProvider
import net.yakuraion.mangakko.main_impl.di.viewmodel.MainViewModelFactoriesModule
import net.yakuraion.mangakko.main_impl.ui.main.view.MainFragment

@Component(
    dependencies = [AppProvider::class],
    modules = [MainViewModelFactoriesModule::class]
)
interface MainComponent {

    fun inject(fragment: MainFragment)

    companion object {

        fun create(appProvider: AppProvider): MainComponent {
            return DaggerMainComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}
