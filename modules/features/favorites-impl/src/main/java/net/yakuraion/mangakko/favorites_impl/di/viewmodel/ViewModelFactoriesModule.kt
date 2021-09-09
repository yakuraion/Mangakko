@file:Suppress("MaxLineLength")

package net.yakuraion.mangakko.favorites_impl.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.di.viewmodel.ViewModelKey
import net.yakuraion.mangakko.favorites_impl.ui.favorites.viewmodel.FavoritesViewModel
import net.yakuraion.mangakko.favorites_impl.ui.favorites_list.viewmodel.FavoritesListViewModel

@Module
interface ViewModelFactoriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    fun bindsFavorites(impl: FavoritesViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesListViewModel::class)
    fun bindsFavoritesList(impl: FavoritesListViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}
