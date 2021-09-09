package net.yakuraion.mangakko.media_impl.ui.media.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.core_uikit.fragment.requireListener
import net.yakuraion.mangakko.core_uikit.onbackpressed.setUpOnBackPressedForClearBackStack
import net.yakuraion.mangakko.media.MediaFeature
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.di.injector
import net.yakuraion.mangakko.media_impl.ui.media.viewmodel.MediaViewModel
import net.yakuraion.mangakko.media_impl.ui.media_list.view.MediaListFragment
import net.yakuraion.mangakko.media_impl.ui.media_overview.view.MediaOverviewFragment
import javax.inject.Inject

@Suppress("EXPERIMENTAL_API_USAGE")
class MediaFragment : BaseFragment<MediaViewModel>(
    MediaViewModel::class,
    R.layout.media_fragment_media
), MediaOverviewFragment.Listener,
    MediaListFragment.Listener {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    private lateinit var featureOwner: MediaFeature.Owner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        featureOwner = requireListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnBackPressedForClearBackStack()
        viewModel.apply {
            showMediaOverviewFragmentLiveData.observe(viewLifecycleOwner) {
                val fragment = MediaOverviewFragment.createFragment()
                showFragment(fragment)
            }
        }
    }

    private fun showFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .apply {
                if (addToBackStack) {
                    addToBackStack(null)
                }
            }
            .commit()
    }

    override fun onMediaOverviewCategoryMoreClick(sortTypes: List<MediaSortType>, status: MediaStatus?) {
        val fragment = MediaListFragment.createFragment(sortTypes, status)
        showFragment(fragment, true)
    }

    override fun onMediaOverviewMediaClick(media: Media) {
        featureOwner.onMediaMediaChosen(media)
    }

    override fun onMediaListMediaClick(media: Media) {
        featureOwner.onMediaMediaChosen(media)
    }

    companion object {

        fun createFragment(): MediaFragment {
            return MediaFragment()
        }
    }
}
