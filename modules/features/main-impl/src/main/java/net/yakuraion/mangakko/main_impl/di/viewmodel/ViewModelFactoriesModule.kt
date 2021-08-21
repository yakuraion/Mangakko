package net.yakuraion.mangakko.main_impl.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.yakuraion.mangakko.core_feature.di.viewmodel.AssistedSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.di.viewmodel.ViewModelKey
import net.yakuraion.mangakko.main_impl.ui.main.viewmodel.MainViewModel

@Module
interface ViewModelFactoriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsMain(impl: MainViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}
