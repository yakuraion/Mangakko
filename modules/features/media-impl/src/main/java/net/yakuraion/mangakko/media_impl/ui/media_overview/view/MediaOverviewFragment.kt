package net.yakuraion.mangakko.media_impl.ui.media_overview.view

import android.content.Context
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.di.injector
import net.yakuraion.mangakko.media_impl.ui.media_overview.viewmodel.MediaOverviewViewModel
import javax.inject.Inject

class MediaOverviewFragment : BaseFragment<MediaOverviewViewModel>(
    MediaOverviewViewModel::class,
    R.layout.media_fragment_media_overview
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    companion object {

        fun createFragment(): MediaOverviewFragment {
            return MediaOverviewFragment()
        }
    }
}
