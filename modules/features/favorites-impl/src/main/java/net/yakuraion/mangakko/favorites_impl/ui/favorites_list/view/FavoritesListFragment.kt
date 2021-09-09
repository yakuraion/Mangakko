package net.yakuraion.mangakko.favorites_impl.ui.favorites_list.view

import android.content.Context
import android.os.Bundle
import android.view.View
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.favorites_impl.R
import net.yakuraion.mangakko.favorites_impl.di.injector
import net.yakuraion.mangakko.favorites_impl.ui.favorites.viewmodel.FavoritesViewModel
import javax.inject.Inject

class FavoritesListFragment : BaseFragment<FavoritesViewModel>(
    FavoritesViewModel::class,
    R.layout.favorites_fragment_favorites_list
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        fun createFragment(): FavoritesListFragment {
            return FavoritesListFragment()
        }
    }
}
