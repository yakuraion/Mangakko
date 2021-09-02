package net.yakuraion.mangakko.media_impl.ui.media_details.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.media_fragment_media_details.coverImageView
import kotlinx.android.synthetic.main.media_fragment_media_details.recyclerView
import kotlinx.android.synthetic.main.media_fragment_media_details.titleTextView
import net.yakuraion.mangakko.core_entity.MediaDetails
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.core_ui.calculateTextColorByBackground
import net.yakuraion.mangakko.core_ui.dpToPxInt
import net.yakuraion.mangakko.core_ui.itemdecorator.setItemMargins
import net.yakuraion.mangakko.core_ui.statusBarColor
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.di.injector
import net.yakuraion.mangakko.media_impl.ui.media_details.view.items.MediaDetailsDescriptionItem
import net.yakuraion.mangakko.media_impl.ui.media_details.viewmodel.MediaDetailsViewModel
import net.yakuraion.mangakko.media_impl.ui.media_details.viewmodel.MediaDetailsViewModel.Companion.ARG_MEDIA_ID
import javax.inject.Inject

class MediaDetailsFragment : BaseFragment<MediaDetailsViewModel>(
    MediaDetailsViewModel::class,
    R.layout.media_fragment_media_details
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    private var previousStatusBarColor: Int = 0

    private val descriptionItemAdapter: ItemAdapter<MediaDetailsDescriptionItem> = ItemAdapter()

    private val adapter: FastAdapter<*> = FastAdapter.with(descriptionItemAdapter)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        previousStatusBarColor = statusBarColor
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        viewModel.apply {
            mediaDetailsLiveData.observe(viewLifecycleOwner) { updateMediaViews(it) }
        }
    }

    private fun setUpRecyclerView() {
        recyclerView.apply {
            adapter = this@MediaDetailsFragment.adapter
            setItemMargins(
                RECYCLER_VIEW_PADDING_DP.dpToPxInt(),
                RECYCLER_VIEW_PADDING_DP.dpToPxInt()
            )
        }
    }

    private fun updateMediaViews(media: MediaDetails) {
        updateMainColorViews(media.mainColor)
        updateCoverImageView(media.imageUrl)
        titleTextView.text = media.title
        updateDescriptionView(media.description)
    }

    private fun updateMainColorViews(color: Int) {
        requireView().setBackgroundColor(color)
        statusBarColor = color
        titleTextView.setTextColor(calculateTextColorByBackground(requireContext(), color))
    }

    private fun updateCoverImageView(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .into(coverImageView)
    }

    private fun updateDescriptionView(description: String) {
        val item = MediaDetailsDescriptionItem(description)
        descriptionItemAdapter.set(listOf(item))
    }

    override fun onDestroyView() {
        recyclerView.adapter = null
        statusBarColor = previousStatusBarColor
        super.onDestroyView()
    }

    companion object {

        private const val RECYCLER_VIEW_PADDING_DP = 16f

        fun createFragment(mediaId: Int): MediaDetailsFragment {
            return MediaDetailsFragment().apply {
                arguments = bundleOf(
                    ARG_MEDIA_ID to mediaId
                )
            }
        }
    }
}