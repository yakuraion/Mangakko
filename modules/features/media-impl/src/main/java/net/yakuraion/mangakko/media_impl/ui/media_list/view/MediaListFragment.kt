package net.yakuraion.mangakko.media_impl.ui.media_list.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.AsyncDifferConfig
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.paged.PagedModelAdapter
import kotlinx.android.synthetic.main.media_fragment_media_list.recyclerView
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_entity.MediaType
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.core_uikit.dpToPxInt
import net.yakuraion.mangakko.core_uikit.fastadapter.items.media.MediaDiffUtilItemCallback
import net.yakuraion.mangakko.core_uikit.fastadapter.items.media.MediaItem
import net.yakuraion.mangakko.core_uikit.fragment.requireListener
import net.yakuraion.mangakko.core_uikit.itemdecorator.setItemMargins
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.di.injector
import net.yakuraion.mangakko.media_impl.ui.media_list.viewmodel.MediaListViewModel
import net.yakuraion.mangakko.media_impl.ui.media_list.viewmodel.MediaListViewModel.Companion.ARG_MEDIA_TYPE
import net.yakuraion.mangakko.media_impl.ui.media_list.viewmodel.MediaListViewModel.Companion.ARG_SORT_TYPES
import net.yakuraion.mangakko.media_impl.ui.media_list.viewmodel.MediaListViewModel.Companion.ARG_STATUS
import javax.inject.Inject

@Suppress("EXPERIMENTAL_API_USAGE")
class MediaListFragment : BaseFragment<MediaListViewModel>(
    MediaListViewModel::class,
    R.layout.media_fragment_media_list
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    private lateinit var listener: Listener

    private val placeholderItemAdapter: ItemAdapter<MediaItem> = ItemAdapter()

    private val itemAdapter: PagedModelAdapter<Media, MediaItem> = PagedModelAdapter(
        AsyncDifferConfig.Builder(MediaDiffUtilItemCallback()).build(),
        { MediaItem(null) }
    ) { MediaItem(it) }

    private val adapter: FastAdapter<MediaItem> = FastAdapter.with(
        listOf(placeholderItemAdapter, itemAdapter)
    ).apply {
        onClickListener = { _, _, item, _ ->
            item.model?.let { listener.onMediaListMediaClick(it) }
            true
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        listener = requireListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpInsets()
        viewModel.apply {
            mediaPagedListLiveData.observe(viewLifecycleOwner) { itemAdapter.submitList(it) }
            placeholderCountLiveData.observe(viewLifecycleOwner) { updatePlaceholderCount(it) }
        }
    }

    private fun setUpRecyclerView() {
        recyclerView.apply {
            adapter = this@MediaListFragment.adapter
            setItemMargins(
                RECYCLER_VIEW_PADDING_DP.dpToPxInt(),
                RECYCLER_VIEW_PADDING_DP.dpToPxInt()
            )
        }
    }

    private fun setUpInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(recyclerView) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(top = insets.top)
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun updatePlaceholderCount(count: Int?) {
        val items = List(count ?: 0) { MediaItem(null) }
        placeholderItemAdapter.apply {
            set(items)
            active = count != null
        }
    }

    override fun onDestroyView() {
        recyclerView.adapter = null
        super.onDestroyView()
    }

    interface Listener {

        fun onMediaListMediaClick(media: Media)
    }

    companion object {

        private const val RECYCLER_VIEW_PADDING_DP = 8f

        fun createFragment(
            sortTypes: List<MediaSortType>,
            mediaType: MediaType?,
            status: MediaStatus?
        ): MediaListFragment {
            return MediaListFragment().apply {
                arguments = bundleOf(
                    ARG_SORT_TYPES to sortTypes,
                    ARG_MEDIA_TYPE to mediaType,
                    ARG_STATUS to status
                )
            }
        }
    }
}
