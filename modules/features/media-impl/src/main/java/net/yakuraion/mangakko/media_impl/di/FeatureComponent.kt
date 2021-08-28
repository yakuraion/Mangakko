package net.yakuraion.mangakko.media_impl.di

import dagger.Component
import net.yakuraion.mangakko.core_di.app_provider.AppProvider
import net.yakuraion.mangakko.media_impl.di.viewmodel.ViewModelFactoriesModule
import net.yakuraion.mangakko.media_impl.ui.media.view.MediaFragment

@Component(
    dependencies = [AppProvider::class],
    modules = [ViewModelFactoriesModule::class]
)
interface FeatureComponent {

    fun inject(fragment: MediaFragment)

    companion object {

        fun create(appProvider: AppProvider): FeatureComponent {
            return DaggerFeatureComponent.builder()
                .appProvider(appProvider)
                .build()
        }
    }
}
