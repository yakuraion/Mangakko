package net.yakuraion.mangakko.genres_impl.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.di.viewmodel.ViewModelKey
import net.yakuraion.mangakko.genres_impl.ui.genres.viewmodel.GenresViewModel

@Module
interface ViewModelFactoriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(GenresViewModel::class)
    fun bindsGenres(impl: GenresViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}
