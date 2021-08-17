package net.yakuraion.mangakko.core_di

import com.google.gson.Gson

interface ConvertersProvider {

    fun provideGson(): Gson
}
