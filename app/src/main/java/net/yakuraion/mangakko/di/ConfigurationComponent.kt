package net.yakuraion.mangakko.di

import dagger.BindsInstance
import dagger.Component
import net.yakuraion.mangakko.BuildConfig
import net.yakuraion.mangakko.core_di.configuration.Configuration
import net.yakuraion.mangakko.core_di.configuration.ConfigurationProvider
import javax.inject.Singleton

@Singleton
@Component
interface ConfigurationComponent : ConfigurationProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindsConfiguration(configuration: Configuration): Builder

        fun build(): ConfigurationComponent
    }

    companion object {

        fun create(): ConfigurationComponent {
            return DaggerConfigurationComponent.builder()
                .bindsConfiguration(getConfiguration())
                .build()
        }

        private fun getConfiguration(): Configuration {
            return Configuration(BuildConfig.SERVER_URL)
        }
    }
}
