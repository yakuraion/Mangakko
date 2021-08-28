package net.yakuraion.mangakko.media_impl.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.di.viewmodel.ViewModelKey
import net.yakuraion.mangakko.media_impl.ui.media.viewmodel.MediaViewModel

@Module
interface ViewModelFactoriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(MediaViewModel::class)
    fun bindsMedia(impl: MediaViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}
