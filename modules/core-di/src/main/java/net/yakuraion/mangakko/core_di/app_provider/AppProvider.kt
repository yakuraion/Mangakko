package net.yakuraion.mangakko.core_di.app_provider

import net.yakuraion.mangakko.core_di.FeaturesProvider
import net.yakuraion.mangakko.core_di.RepositoriesProvider
import net.yakuraion.mangakko.core_di.dispatchers.DispatchersProvider

interface AppProvider :
    FeaturesProvider,
    RepositoriesProvider,
    DispatchersProvider
