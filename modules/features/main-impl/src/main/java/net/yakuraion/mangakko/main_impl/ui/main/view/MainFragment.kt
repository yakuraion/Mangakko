package net.yakuraion.mangakko.main_impl.ui.main.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.core_uikit.onbackpressed.setUpOnBackPressedForClearBackStack
import net.yakuraion.mangakko.core_utils.applyIf
import net.yakuraion.mangakko.main_impl.di.injector
import net.yakuraion.mangakko.main_impl.ui.main.viewmodel.MainViewModel
import net.yakuraion.mangakko.media.MediaFeature
import net.yakuraion.mangakko.pager.PagerFeature
import net.yakuraion.mangakko_impl.R
import javax.inject.Inject

class MainFragment : BaseFragment<MainViewModel>(
    MainViewModel::class,
    R.layout.main_fragment_main
), PagerFeature.Owner {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    @Inject
    lateinit var pagerFeature: PagerFeature

    @Inject
    lateinit var mediaFeature: MediaFeature

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnBackPressedForClearBackStack()
        viewModel.apply {
            showPagerFragmentLiveData.observe(viewLifecycleOwner) {
                showFragment(pagerFeature.getPagerFragment())
            }
        }
    }

    override fun onPagerMediaChosen(media: Media) {
        val fragment = mediaFeature.getMediaDetailsFragment(media)
        showFragment(fragment, true)
    }

    private fun showFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .applyIf(addToBackStack) { addToBackStack(null) }
            .commit()
    }

    companion object {

        fun createFragment(): MainFragment {
            return MainFragment()
        }
    }
}
