package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.core_network.MediaApi

interface NetworkProvider {

    fun provideMediaApi(): MediaApi
}
