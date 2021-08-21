package net.yakuraion.mangakko.genres_impl.di

import dagger.Component
import net.yakuraion.mangakko.core_di.app_provider.AppProvider
import net.yakuraion.mangakko.genres_impl.di.viewmodel.ViewModelFactoriesModule
import net.yakuraion.mangakko.genres_impl.ui.genres.view.GenresFragment

@Component(
    dependencies = [AppProvider::class],
    modules = [ViewModelFactoriesModule::class]
)
interface FeatureComponent {

    fun inject(fragment: GenresFragment)

    companion object {

        fun create(appProvider: AppProvider): FeatureComponent {
            return DaggerFeatureComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}
