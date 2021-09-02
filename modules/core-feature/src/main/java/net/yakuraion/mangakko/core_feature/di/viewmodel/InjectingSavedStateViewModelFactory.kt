package net.yakuraion.mangakko.core_feature.di.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import dagger.Reusable
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

@Reusable
class InjectingSavedStateViewModelFactory @Inject constructor(
    @Suppress("MaxLineLength")
    private val assistedFactoryProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards AssistedFactoryProvider>
) {

    @Suppress("UNCHECKED_CAST")
    fun <VM : ViewModel, Owner> create(
        viewModelClass: KClass<out ViewModel>,
        owner: Owner,
        defaultArgs: Bundle? = null
    ): VM
            where Owner : SavedStateRegistryOwner,
                  Owner : ViewModelStoreOwner {
        val factory = object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                assistedFactoryProviders[modelClass]?.get()?.let { factory ->
                    try {
                        return factory.create(handle) as T
                    } catch (e: Exception) {
                        throw RuntimeException(e)
                    }
                } ?: throw IllegalArgumentException("Unknown model class $modelClass")
            }
        }
        try {
            return ViewModelProvider(owner, factory)[viewModelClass.java] as VM
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

typealias AssistedFactoryProvider = Provider<AssistedSavedStateViewModelFactory<out ViewModel>>
