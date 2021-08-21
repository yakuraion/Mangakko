package net.yakuraion.mangakko.core_di

import net.yakuraion.mangakko.core_network.GenresApi

interface NetworkProvider {

    fun provideGenresApi(): GenresApi
}
