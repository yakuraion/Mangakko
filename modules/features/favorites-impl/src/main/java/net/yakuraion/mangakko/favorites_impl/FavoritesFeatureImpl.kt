package net.yakuraion.mangakko.favorites_impl

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.favorites.FavoritesFeature
import net.yakuraion.mangakko.favorites_impl.ui.favorites.view.FavoritesFragment
import javax.inject.Inject

class FavoritesFeatureImpl @Inject constructor() : FavoritesFeature {

    override fun getFavoritesFragment(): Fragment {
        return FavoritesFragment.createFragment()
    }
}
