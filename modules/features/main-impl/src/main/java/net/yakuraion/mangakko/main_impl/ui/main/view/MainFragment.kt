package net.yakuraion.mangakko.main_impl.ui.main.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.genres.GenresFeature
import net.yakuraion.mangakko.main_impl.di.injector
import net.yakuraion.mangakko.main_impl.ui.main.viewmodel.MainViewModel
import net.yakuraion.mangakko_impl.R
import javax.inject.Inject

class MainFragment : BaseFragment<MainViewModel>(MainViewModel::class, R.layout.main_fragment_main) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    @Inject
    lateinit var genresFeature: GenresFeature

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            showGenresFragmentLiveData.observe(viewLifecycleOwner) {
                showFragment(genresFeature.getGenresFragment())
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    companion object {

        fun createFragment(): MainFragment {
            return MainFragment()
        }
    }
}
