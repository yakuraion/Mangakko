package net.yakuraion.mangakko.core_network_impl.di

import dagger.Module
import net.yakuraion.mangakko.core_network_impl.di.modules.ApolloModule

@Module(includes = [ApolloModule::class])
interface NetworkModule
