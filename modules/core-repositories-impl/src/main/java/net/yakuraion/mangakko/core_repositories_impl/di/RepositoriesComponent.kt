package net.yakuraion.mangakko.core_repositories_impl.di

import dagger.Component
import net.yakuraion.mangakko.core_di.NetworkProvider
import net.yakuraion.mangakko.core_di.PersistenceProvider
import net.yakuraion.mangakko.core_di.RepositoriesProvider
import net.yakuraion.mangakko.core_di.configuration.ConfigurationProvider
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        NetworkProvider::class,
        PersistenceProvider::class,
        ConfigurationProvider::class
    ],
    modules = [RepositoriesModule::class]
)
interface RepositoriesComponent : RepositoriesProvider {

    companion object {

        fun create(
            networkProvider: NetworkProvider,
            persistenceProvider: PersistenceProvider,
            configurationProvider: ConfigurationProvider
        ): RepositoriesComponent {
            return DaggerRepositoriesComponent.builder()
                .persistenceProvider(persistenceProvider)
                .configurationProvider(configurationProvider)
                .networkProvider(networkProvider)
                .build()
        }
    }
}
