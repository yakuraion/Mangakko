package net.yakuraion.mangakko.media_impl.ui.media_details.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.core.view.updatePadding
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.media_fragment_media_details_content.appBarLayout
import kotlinx.android.synthetic.main.media_fragment_media_details_content.coverImageView
import kotlinx.android.synthetic.main.media_fragment_media_details_content.recyclerView
import kotlinx.android.synthetic.main.media_fragment_media_details_content.titleTextView
import kotlinx.android.synthetic.main.media_fragment_media_details_placeholder.coverPlaceholderView
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.core_ui.calculateTextColorByBackground
import net.yakuraion.mangakko.core_ui.content.ContentStateController
import net.yakuraion.mangakko.core_ui.dpToPxInt
import net.yakuraion.mangakko.core_ui.itemdecorator.setItemMargins
import net.yakuraion.mangakko.core_ui.resolveColorAttr
import net.yakuraion.mangakko.core_ui.statusBarColor
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.di.injector
import net.yakuraion.mangakko.media_impl.ui.media_details.view.items.MediaDetailsDescriptionItem
import net.yakuraion.mangakko.media_impl.ui.media_details.viewmodel.MediaDetailsViewModel
import net.yakuraion.mangakko.media_impl.ui.media_details.viewmodel.MediaDetailsViewModel.Companion.ARG_MEDIA
import net.yakuraion.mangakko.media_impl.ui.media_details.viewmodel.MediaDetailsViewModel.Companion.ARG_MEDIA_ID
import javax.inject.Inject
import kotlin.math.abs

class MediaDetailsFragment : BaseFragment<MediaDetailsViewModel>(
    MediaDetailsViewModel::class,
    R.layout.media_fragment_media_details
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    private val contentStateController: ContentStateController = ContentStateController(
        contentViewIds = listOf(R.id.contentLayout),
        progressViewIds = listOf(R.id.placeholderLayout)
    )

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
        contentStateController.attachView(view)
        setUpStatusBarColor()
        setUpInsets()
        setUpRecyclerView()
        setUpInitMainColorViews()
        viewModel.apply {
            contentStateLiveData.observe(viewLifecycleOwner) { contentStateController.state = it }
            mainColorLiveData.observe(viewLifecycleOwner) { updateMainColorViews(it) }
            titleLiveData.observe(viewLifecycleOwner) { titleTextView.text = it }
            coverImageUrlLiveData.observe(viewLifecycleOwner) { updateCoverImageView(it) }
            descriptionLiveData.observe(viewLifecycleOwner) { updateDescriptionView(it) }
        }
    }

    private fun setUpStatusBarColor() {
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val isAppBarLayoutVisible = abs(verticalOffset) != appBarLayout.totalScrollRange
            statusBarColor = if (isAppBarLayoutVisible) {
                Color.TRANSPARENT
            } else {
                requireContext().resolveColorAttr(R.attr.statusBarColor)
            }
        })
    }

    private fun setUpInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(requireView()) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            listOf(coverImageView, coverPlaceholderView).forEach { view ->
                view.updateLayoutParams<MarginLayoutParams> {
                    updateMargins(top = COVER_IMAGE_TOP_MARGIN_DP.dpToPxInt() + insets.top)
                }
            }
            recyclerView.updatePadding(top = insets.top, bottom = insets.bottom)
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun setUpRecyclerView() {
        recyclerView.apply {
            adapter = this@MediaDetailsFragment.adapter
            setItemMargins(
                RECYCLER_VIEW_HORIZONTAL_PADDING_DP.dpToPxInt(),
                RECYCLER_VIEW_VERTICAL_PADDING_DP.dpToPxInt(),
                RECYCLER_VIEW_HORIZONTAL_PADDING_DP.dpToPxInt(),
                RECYCLER_VIEW_HORIZONTAL_PADDING_DP.dpToPxInt(),
                RECYCLER_VIEW_TOP_PADDING_DP.dpToPxInt(),
                RECYCLER_VIEW_VERTICAL_PADDING_DP.dpToPxInt()
            )
        }
    }

    private fun setUpInitMainColorViews() {
        val color = requireContext().resolveColorAttr(R.attr.windowBackgroundColor)
        updateMainColorViews(color)
    }

    private fun updateMainColorViews(color: Int) {
        requireView().setBackgroundColor(color)
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
        contentStateController.detachView()
        recyclerView.adapter = null
        statusBarColor = previousStatusBarColor
        super.onDestroyView()
    }

    companion object {

        private const val RECYCLER_VIEW_HORIZONTAL_PADDING_DP = 16f
        private const val RECYCLER_VIEW_VERTICAL_PADDING_DP = 16f
        private const val RECYCLER_VIEW_TOP_PADDING_DP = 4f

        private const val COVER_IMAGE_TOP_MARGIN_DP = 16f

        fun createFragment(mediaId: Int): MediaDetailsFragment {
            return MediaDetailsFragment().apply {
                arguments = bundleOf(
                    ARG_MEDIA_ID to mediaId
                )
            }
        }

        fun createFragment(media: Media): MediaDetailsFragment {
            return MediaDetailsFragment().apply {
                arguments = bundleOf(
                    ARG_MEDIA_ID to media.id,
                    ARG_MEDIA to media
                )
            }
        }
    }
}
