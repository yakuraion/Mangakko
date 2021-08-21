package net.yakuraion.mangakko.di

import dagger.Component
import net.yakuraion.mangakko.AppActivity
import net.yakuraion.mangakko.core_di.RepositoriesProvider
import net.yakuraion.mangakko.core_di.app_provider.AppProvider
import net.yakuraion.mangakko.core_di.dispatchers.DispatchersProvider
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [RepositoriesProvider::class, DispatchersProvider::class],
    modules = [FeaturesModule::class]
)
interface AppComponent : AppProvider {

    fun inject(activity: AppActivity)

    companion object {

        fun create(
            repositoriesProvider: RepositoriesProvider,
            dispatchersProvider: DispatchersProvider
        ): AppComponent {
            return DaggerAppComponent.builder()
                .repositoriesProvider(repositoriesProvider)
                .dispatchersProvider(dispatchersProvider)
                .build()
        }
    }
}
