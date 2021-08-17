package net.yakuraion.mangakko.main_impl.di

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.core_di.app_provider.AppProviderHolder

val Fragment.injector: MainComponent
    get() {
        val appProvider = (requireActivity().application as AppProviderHolder).appProvider
        return MainComponent.create(appProvider)
    }
