package net.yakuraion.mangakko.core_persistence_impl.di

import dagger.Component
import net.yakuraion.mangakko.core_di.ContextProvider
import net.yakuraion.mangakko.core_di.ConvertersProvider
import net.yakuraion.mangakko.core_di.PersistenceProvider
import javax.inject.Singleton

@Singleton
@Component(dependencies = [ContextProvider::class, ConvertersProvider::class], modules = [PersistenceModule::class])
interface PersistenceComponent : PersistenceProvider {

    companion object {

        fun create(contextProvider: ContextProvider, convertersProvider: ConvertersProvider): PersistenceComponent {
            return DaggerPersistenceComponent.builder()
                .contextProvider(contextProvider)
                .convertersProvider(convertersProvider)
                .build()
        }
    }
}
