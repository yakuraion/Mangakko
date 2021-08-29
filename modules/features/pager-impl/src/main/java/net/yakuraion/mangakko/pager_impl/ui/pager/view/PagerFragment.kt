package net.yakuraion.mangakko.pager_impl.ui.pager.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.pager_fragment_pager.bottomNavigationView
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.core_ui.onbackpressed.addCallback
import net.yakuraion.mangakko.media.MediaFeature
import net.yakuraion.mangakko.pager_impl.R
import net.yakuraion.mangakko.pager_impl.di.injector
import net.yakuraion.mangakko.pager_impl.ui.pager.view.PagerFragment.Page.FAVORITES
import net.yakuraion.mangakko.pager_impl.ui.pager.view.PagerFragment.Page.HOME
import net.yakuraion.mangakko.pager_impl.ui.pager.view.PagerFragment.Page.ONGOINGS
import net.yakuraion.mangakko.pager_impl.ui.pager.view.PagerFragment.Page.SETTINGS
import net.yakuraion.mangakko.pager_impl.ui.pager.viewmodel.PagerViewModel
import javax.inject.Inject

class PagerFragment : BaseFragment<PagerViewModel>(
    PagerViewModel::class,
    R.layout.pager_fragment_pager
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    @Inject
    lateinit var mediaFeature: MediaFeature

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnBackPressedCallback()
        setUpBottomNavigationView()
        viewModel.apply {
            openFirstPageLiveData.observe(viewLifecycleOwner) { openPage(HOME) }
        }
    }

    private fun setUpOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), viewLifecycleOwner) {
            if (bottomNavigationView.selectedItemId != HOME.itemId) {
                bottomNavigationView.selectedItemId = HOME.itemId
                true
            } else {
                false
            }
        }
    }

    private fun setUpBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            val page = when (menuItem.itemId) {
                R.id.itemHome -> HOME
                R.id.itemOngoings -> ONGOINGS
                R.id.itemFavorites -> FAVORITES
                R.id.itemSettings -> SETTINGS
                else -> error("Unexpected itemId")
            }
            openPage(page)
            true
        }
    }

    private fun openPage(page: Page) {
        childFragmentManager.beginTransaction()
            .apply {
                showOrAddPage(page)
                Page.values().filter { it != page }
                    .forEach { pageToHide -> pageToHide.getFragment()?.let { hide(it) } }
            }
            .commit()
    }

    private fun FragmentTransaction.showOrAddPage(page: Page) {
        page.getFragment()?.let { show(it) }
            ?: add(R.id.fragmentContainerView, page.createFragment(), page.name)
    }

    private fun Page.getFragment(): Fragment? = childFragmentManager.findFragmentByTag(name)

    private fun Page.createFragment(): Fragment {
        return when (this) {
            HOME -> mediaFeature.getMediaOverviewFragment()
            ONGOINGS -> Fragment()
            FAVORITES -> Fragment()
            SETTINGS -> Fragment()
        }
    }

    enum class Page(@IdRes val itemId: Int) {
        HOME(R.id.itemHome),
        ONGOINGS(R.id.itemOngoings),
        FAVORITES(R.id.itemFavorites),
        SETTINGS(R.id.itemSettings)
    }

    companion object {

        fun createFragment(): PagerFragment {
            return PagerFragment()
        }
    }
}
