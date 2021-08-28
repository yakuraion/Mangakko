package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.core_network.GenresApi
import net.yakuraion.mangakko.core_network.MediaApi

interface NetworkProvider {

    fun provideGenresApi(): GenresApi

    fun provideMediaApi(): MediaApi
}
