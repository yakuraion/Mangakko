package net.yakuraion.mangakko.core_di.app_provider

import net.yakuraion.mangakko.core_di.FeaturesProvider
import net.yakuraion.mangakko.core_di.RepositoriesProvider
import net.yakuraion.mangakko.core_di.dispatchers.DispatchersProvider

/**
 * Интерфейс, предоставляющий доступ к объектам, которые необходимы для feature-модулей.
 */
interface AppProvider :
    FeaturesProvider,
    RepositoriesProvider,
    DispatchersProvider
