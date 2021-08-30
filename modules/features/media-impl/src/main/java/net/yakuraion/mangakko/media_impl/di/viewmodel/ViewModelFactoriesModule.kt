package net.yakuraion.mangakko.media_impl.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.di.viewmodel.ViewModelKey
import net.yakuraion.mangakko.media_impl.ui.media.viewmodel.MediaViewModel
import net.yakuraion.mangakko.media_impl.ui.media_details.viewmodel.MediaDetailsViewModel
import net.yakuraion.mangakko.media_impl.ui.media_list.viewmodel.MediaListViewModel
import net.yakuraion.mangakko.media_impl.ui.media_overview.viewmodel.MediaOverviewViewModel

@Module
interface ViewModelFactoriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(MediaViewModel::class)
    fun bindsMedia(impl: MediaViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(MediaListViewModel::class)
    fun bindsMediaList(impl: MediaListViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(MediaOverviewViewModel::class)
    fun bindsMediaOverview(impl: MediaOverviewViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(MediaDetailsViewModel::class)
    fun bindsMediaDetails(impl: MediaDetailsViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}
