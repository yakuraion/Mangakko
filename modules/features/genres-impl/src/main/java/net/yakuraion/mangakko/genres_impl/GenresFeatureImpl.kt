package net.yakuraion.mangakko.genres_impl

import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.genres.GenresFeature
import net.yakuraion.mangakko.genres_impl.ui.genres.view.GenresFragment
import javax.inject.Inject

class GenresFeatureImpl @Inject constructor() : GenresFeature {

    override fun getGenresFragment(): Fragment {
        return GenresFragment.createFragment()
    }
}
