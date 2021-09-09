package net.yakuraion.mangakko.favorites_impl.di

import dagger.Component
import net.yakuraion.mangakko.core_di.app_provider.AppProvider
import net.yakuraion.mangakko.favorites_impl.di.viewmodel.ViewModelFactoriesModule
import net.yakuraion.mangakko.favorites_impl.ui.favorites.view.FavoritesFragment
import net.yakuraion.mangakko.favorites_impl.ui.favorites_list.view.FavoritesListFragment

@Component(
    dependencies = [AppProvider::class],
    modules = [ViewModelFactoriesModule::class]
)
interface FeatureComponent {

    fun inject(fragment: FavoritesFragment)

    fun inject(fragment: FavoritesListFragment)

    companion object {

        fun create(appProvider: AppProvider): FeatureComponent {
            return DaggerFeatureComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}
