package net.yakuraion.mangakko.pager_impl.ui.pager.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.annotation.IdRes
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.pager_fragment_pager.bottomNavigationView
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.core_uikit.fragment.requireListener
import net.yakuraion.mangakko.core_uikit.onbackpressed.addCallback
import net.yakuraion.mangakko.favorites.FavoritesFeature
import net.yakuraion.mangakko.media.MediaFeature
import net.yakuraion.mangakko.pager.PagerFeature
import net.yakuraion.mangakko.pager_impl.R
import net.yakuraion.mangakko.pager_impl.di.injector
import net.yakuraion.mangakko.pager_impl.ui.pager.view.PagerFragment.Page.FAVORITES
import net.yakuraion.mangakko.pager_impl.ui.pager.view.PagerFragment.Page.HOME
import net.yakuraion.mangakko.pager_impl.ui.pager.view.PagerFragment.Page.SETTINGS
import net.yakuraion.mangakko.pager_impl.ui.pager.viewmodel.PagerViewModel
import net.yakuraion.mangakko.settings.SettingsFeature
import javax.inject.Inject

class PagerFragment : BaseFragment<PagerViewModel>(
    PagerViewModel::class,
    R.layout.pager_fragment_pager
), MediaFeature.Owner, FavoritesFeature.Owner {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    private lateinit var featureOwner: PagerFeature.Owner

    @Inject
    lateinit var mediaFeature: MediaFeature

    @Inject
    lateinit var favoritesFeature: FavoritesFeature

    @Inject
    lateinit var settingsFeature: SettingsFeature

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        featureOwner = requireListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpInsets()
        setUpOnBackPressedCallback()
        setUpBottomNavigationView()
        viewModel.apply {
            openFirstPageLiveData.observe(viewLifecycleOwner) { openPage(HOME) }
        }
    }

    private fun setUpInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(requireView()) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            bottomNavigationView.updateLayoutParams<MarginLayoutParams> {
                updateMargins(bottom = insets.bottom)
            }
            val newInsets = Insets.of(insets.left, insets.top, insets.right, 0)
            WindowInsetsCompat.Builder().setInsets(WindowInsetsCompat.Type.systemBars(), newInsets).build()
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
            HOME -> mediaFeature.getMediaFragment()
            FAVORITES -> favoritesFeature.getFavoritesFragment()
            SETTINGS -> settingsFeature.getSettingsFragment()
        }
    }

    enum class Page(@IdRes val itemId: Int) {
        HOME(R.id.itemHome),
        FAVORITES(R.id.itemFavorites),
        SETTINGS(R.id.itemSettings)
    }

    override fun onMediaMediaChosen(media: Media) {
        featureOwner.onPagerMediaChosen(media)
    }

    override fun onFavoritesMediaClick(media: Media) {
        featureOwner.onPagerMediaChosen(media)
    }

    companion object {

        fun createFragment(): PagerFragment {
            return PagerFragment()
        }
    }
}
