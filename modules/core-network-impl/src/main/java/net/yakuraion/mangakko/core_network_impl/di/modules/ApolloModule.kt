package net.yakuraion.mangakko.core_network_impl.di.modules

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import net.yakuraion.mangakko.core_di.configuration.Configuration
import javax.inject.Singleton

@Module
class ApolloModule {

    @Provides
    @Singleton
    fun provideApolloClient(configuration: Configuration): ApolloClient {
        TODO("serverUrl empty")
        return ApolloClient.builder()
            .serverUrl("")
            .build()
    }
}
