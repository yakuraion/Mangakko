package net.yakuraion.mangakko.media_impl.ui.media_overview.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.media_fragment_media_overview.recyclerView
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.MediaSortType
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.core_ui.fragment.requireListener
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.di.injector
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.IN_TREND
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.MOST_POPULAR
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.MOST_RATED
import net.yakuraion.mangakko.media_impl.ui.media_overview.view.items.MediaOverviewListItem
import net.yakuraion.mangakko.media_impl.ui.media_overview.view.items.MediaOverviewListTitleItem
import net.yakuraion.mangakko.media_impl.ui.media_overview.viewmodel.MediaOverviewViewModel
import javax.inject.Inject

class MediaOverviewFragment : BaseFragment<MediaOverviewViewModel>(
    MediaOverviewViewModel::class,
    R.layout.media_fragment_media_overview
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    private lateinit var listener: Listener

    private val inTrendTitleItemAdapter: ItemAdapter<MediaOverviewListTitleItem> =
        ItemAdapter<MediaOverviewListTitleItem>().apply {
            set(listOf(MediaOverviewListTitleItem(IN_TREND)))
        }
    private val inTrendItemAdapter: ItemAdapter<MediaOverviewListItem> = ItemAdapter()

    private val mostPopularTitleItemAdapter: ItemAdapter<MediaOverviewListTitleItem> =
        ItemAdapter<MediaOverviewListTitleItem>().apply {
            set(listOf(MediaOverviewListTitleItem(MOST_POPULAR)))
        }
    private val mostPopularItemAdapter: ItemAdapter<MediaOverviewListItem> = ItemAdapter()

    private val mostRatedTitleItemAdapter: ItemAdapter<MediaOverviewListTitleItem> =
        ItemAdapter<MediaOverviewListTitleItem>().apply {
            set(listOf(MediaOverviewListTitleItem(MOST_RATED)))
        }
    private val mostRatedItemAdapter: ItemAdapter<MediaOverviewListItem> = ItemAdapter()

    private val fastAdapter: FastAdapter<*> = FastAdapter.with(
        listOf(
            inTrendTitleItemAdapter,
            inTrendItemAdapter,
            mostPopularTitleItemAdapter,
            mostPopularItemAdapter,
            mostRatedTitleItemAdapter,
            mostRatedItemAdapter
        )
    ).apply {
        addEventHooks(
            listOf(
                MediaOverviewListTitleItem.MoreClickEventHook { category ->
                    listener.onMediaOverviewCategoryMoreClick(category.toSortTypes())
                },
                MediaOverviewListItem.NestedMediaClickEventHook { media ->
                    listener.onMediaOverviewMediaClick(media.id)
                }
            )
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        listener = requireListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpInsets()
        recyclerView.adapter = fastAdapter
        viewModel.apply {
            inTrendMediaListLiveData.observe(viewLifecycleOwner) { mediaList ->
                updateMediaList(inTrendItemAdapter, mediaList)
            }
            mostPopularMediaListLiveData.observe(viewLifecycleOwner) { mediaList ->
                updateMediaList(mostPopularItemAdapter, mediaList)
            }
            mostRatedMediaListLiveData.observe(viewLifecycleOwner) { mediaList ->
                updateMediaList(mostRatedItemAdapter, mediaList)
            }
        }
    }

    private fun setUpInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(recyclerView) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(top = insets.top)
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun updateMediaList(itemAdapter: ItemAdapter<MediaOverviewListItem>, list: List<Media?>) {
        val item = MediaOverviewListItem(list)
        itemAdapter.set(listOf(item))
    }

    override fun onDestroyView() {
        recyclerView.adapter = null
        super.onDestroyView()
    }

    interface Listener {

        fun onMediaOverviewCategoryMoreClick(sortTypes: List<MediaSortType>)

        fun onMediaOverviewMediaClick(mediaId: Int)
    }

    companion object {

        fun createFragment(): MediaOverviewFragment {
            return MediaOverviewFragment()
        }
    }
}
