package net.yakuraion.mangakko.core_persistence_impl.di

import dagger.Module
import net.yakuraion.mangakko.core_persistence_impl.di.modules.DatabaseModule
import net.yakuraion.mangakko.core_persistence_impl.di.modules.PreferencesModule

@Module(includes = [PreferencesModule::class, DatabaseModule::class])
interface PersistenceModule
