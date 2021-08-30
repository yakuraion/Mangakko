package net.yakuraion.mangakko.media_impl.ui.media_details.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.media_fragment_media_details.textView
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.di.injector
import net.yakuraion.mangakko.media_impl.ui.media_details.viewmodel.MediaDetailsViewModel
import net.yakuraion.mangakko.media_impl.ui.media_details.viewmodel.MediaDetailsViewModel.Companion.ARG_MEDIA_ID
import javax.inject.Inject

class MediaDetailsFragment : BaseFragment<MediaDetailsViewModel>(
    MediaDetailsViewModel::class,
    R.layout.media_fragment_media_details
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = "media details ${viewModel.mediaId}"
    }

    companion object {

        fun createFragment(mediaId: Int): MediaDetailsFragment {
            return MediaDetailsFragment().apply {
                arguments = bundleOf(
                    ARG_MEDIA_ID to mediaId
                )
            }
        }
    }
}
