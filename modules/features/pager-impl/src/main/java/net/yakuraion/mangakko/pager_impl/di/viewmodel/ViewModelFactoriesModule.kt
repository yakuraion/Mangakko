package net.yakuraion.mangakko.pager_impl.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.di.viewmodel.ViewModelKey
import net.yakuraion.mangakko.pager_impl.ui.pager.viewmodel.PagerViewModel

@Module
interface ViewModelFactoriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(PagerViewModel::class)
    fun bindsPager(impl: PagerViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}
