package net.yakuraion.mangakko.favorites_impl.ui.favorites.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.core_uikit.fragment.requireListener
import net.yakuraion.mangakko.core_uikit.onbackpressed.setUpOnBackPressedForClearBackStack
import net.yakuraion.mangakko.core_utils.applyIf
import net.yakuraion.mangakko.favorites.FavoritesFeature
import net.yakuraion.mangakko.favorites_impl.R
import net.yakuraion.mangakko.favorites_impl.di.injector
import net.yakuraion.mangakko.favorites_impl.ui.favorites.viewmodel.FavoritesViewModel
import net.yakuraion.mangakko.favorites_impl.ui.favorites_list.view.FavoritesListFragment
import javax.inject.Inject

class FavoritesFragment : BaseFragment<FavoritesViewModel>(
    FavoritesViewModel::class,
    R.layout.favorites_fragment_favorites
), FavoritesListFragment.Listener {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    private lateinit var featureOwner: FavoritesFeature.Owner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        featureOwner = requireListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnBackPressedForClearBackStack()
        viewModel.apply {
            showFavoritesListFragmentLiveData.observe(viewLifecycleOwner) {
                val fragment = FavoritesListFragment.createFragment()
                showFragment(fragment)
            }
        }
    }

    private fun showFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .applyIf(addToBackStack) { addToBackStack(null) }
            .commit()
    }

    override fun onFavoritesListMediaClick(media: Media) {
        featureOwner.onFavoritesMediaClick(media)
    }

    companion object {

        fun createFragment(): FavoritesFragment {
            return FavoritesFragment()
        }
    }
}
