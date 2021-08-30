package net.yakuraion.mangakko.media_impl.di

import dagger.Component
import net.yakuraion.mangakko.core_di.app_provider.AppProvider
import net.yakuraion.mangakko.media_impl.di.viewmodel.ViewModelFactoriesModule
import net.yakuraion.mangakko.media_impl.ui.media.view.MediaFragment
import net.yakuraion.mangakko.media_impl.ui.media_details.view.MediaDetailsFragment
import net.yakuraion.mangakko.media_impl.ui.media_list.view.MediaListFragment
import net.yakuraion.mangakko.media_impl.ui.media_overview.view.MediaOverviewFragment

@Component(
    dependencies = [AppProvider::class],
    modules = [ViewModelFactoriesModule::class]
)
interface FeatureComponent {

    fun inject(fragment: MediaFragment)

    fun inject(fragment: MediaListFragment)

    fun inject(fragment: MediaOverviewFragment)

    fun inject(fragment: MediaDetailsFragment)

    companion object {

        fun create(appProvider: AppProvider): FeatureComponent {
            return DaggerFeatureComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}
