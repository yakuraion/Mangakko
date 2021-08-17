package net.yakuraion.mangakko.core_network_impl.di

import dagger.Component
import net.yakuraion.mangakko.core_di.ContextProvider
import net.yakuraion.mangakko.core_di.ConvertersProvider
import net.yakuraion.mangakko.core_di.NetworkProvider
import net.yakuraion.mangakko.core_di.configuration.ConfigurationProvider
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        ConvertersProvider::class,
        ContextProvider::class,
        ConfigurationProvider::class
    ]
)
interface NetworkComponent : NetworkProvider {

    companion object {

        fun create(
            contextProvider: ContextProvider,
            convertersProvider: ConvertersProvider,
            configurationProvider: ConfigurationProvider,
        ): NetworkComponent {
            return DaggerNetworkComponent.builder()
                .contextProvider(contextProvider)
                .convertersProvider(convertersProvider)
                .configurationProvider(configurationProvider)
                .build()
        }
    }
}
