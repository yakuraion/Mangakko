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
import net.yakuraion.mangakko.core_entity.MediaStatus
import net.yakuraion.mangakko.core_entity.MediaType
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.core_uikit.fragment.requireListener
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.di.injector
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.MOST_POPULAR_ANIME
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.MOST_POPULAR_MANGA
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.MOST_RATED_ANIME
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.MOST_RATED_MANGA
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.ONGOING_ANIME
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.ONGOING_MANGA
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

    private val ongoingAnimeTitleItemAdapter: ItemAdapter<MediaOverviewListTitleItem> =
        ItemAdapter<MediaOverviewListTitleItem>().apply {
            set(listOf(MediaOverviewListTitleItem(ONGOING_ANIME)))
        }
    private val ongoingAnimeItemAdapter: ItemAdapter<MediaOverviewListItem> = ItemAdapter()

    private val mostPopularAnimeTitleItemAdapter: ItemAdapter<MediaOverviewListTitleItem> =
        ItemAdapter<MediaOverviewListTitleItem>().apply {
            set(listOf(MediaOverviewListTitleItem(MOST_POPULAR_ANIME)))
        }
    private val mostPopularAnimeItemAdapter: ItemAdapter<MediaOverviewListItem> = ItemAdapter()

    private val mostRatedAnimeTitleItemAdapter: ItemAdapter<MediaOverviewListTitleItem> =
        ItemAdapter<MediaOverviewListTitleItem>().apply {
            set(listOf(MediaOverviewListTitleItem(MOST_RATED_ANIME)))
        }
    private val mostRatedAnimeItemAdapter: ItemAdapter<MediaOverviewListItem> = ItemAdapter()

    private val ongoingMangaTitleItemAdapter: ItemAdapter<MediaOverviewListTitleItem> =
        ItemAdapter<MediaOverviewListTitleItem>().apply {
            set(listOf(MediaOverviewListTitleItem(ONGOING_MANGA)))
        }
    private val ongoingMangaItemAdapter: ItemAdapter<MediaOverviewListItem> = ItemAdapter()

    private val mostPopularMangaTitleItemAdapter: ItemAdapter<MediaOverviewListTitleItem> =
        ItemAdapter<MediaOverviewListTitleItem>().apply {
            set(listOf(MediaOverviewListTitleItem(MOST_POPULAR_MANGA)))
        }
    private val mostPopularMangaItemAdapter: ItemAdapter<MediaOverviewListItem> = ItemAdapter()

    private val mostRatedMangaTitleItemAdapter: ItemAdapter<MediaOverviewListTitleItem> =
        ItemAdapter<MediaOverviewListTitleItem>().apply {
            set(listOf(MediaOverviewListTitleItem(MOST_RATED_MANGA)))
        }
    private val mostRatedMangaItemAdapter: ItemAdapter<MediaOverviewListItem> = ItemAdapter()

    private val fastAdapter: FastAdapter<*> = FastAdapter.with(
        listOf(
            ongoingAnimeTitleItemAdapter,
            ongoingAnimeItemAdapter,
            mostPopularAnimeTitleItemAdapter,
            mostPopularAnimeItemAdapter,
            mostRatedAnimeTitleItemAdapter,
            mostRatedAnimeItemAdapter,
            ongoingMangaTitleItemAdapter,
            ongoingMangaItemAdapter,
            mostPopularMangaTitleItemAdapter,
            mostPopularMangaItemAdapter,
            mostRatedMangaTitleItemAdapter,
            mostRatedMangaItemAdapter,
        )
    ).apply {
        addEventHooks(
            listOf(
                MediaOverviewListTitleItem.MoreClickEventHook { category ->
                    listener.onMediaOverviewCategoryMoreClick(
                        category.sortTypes,
                        category.mediaType,
                        category.status
                    )
                },
                MediaOverviewListItem.NestedMediaClickEventHook { media ->
                    listener.onMediaOverviewMediaClick(media)
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
            ongoingAnimeListLiveData.observe(viewLifecycleOwner) { mediaList ->
                updateMediaList(ongoingAnimeItemAdapter, mediaList)
            }
            mostPopularAnimeListLiveData.observe(viewLifecycleOwner) { mediaList ->
                updateMediaList(mostPopularAnimeItemAdapter, mediaList)
            }
            mostRatedAnimeListLiveData.observe(viewLifecycleOwner) { mediaList ->
                updateMediaList(mostRatedAnimeItemAdapter, mediaList)
            }
            ongoingMangaListLiveData.observe(viewLifecycleOwner) { mediaList ->
                updateMediaList(ongoingMangaItemAdapter, mediaList)
            }
            mostPopularMangaListLiveData.observe(viewLifecycleOwner) { mediaList ->
                updateMediaList(mostPopularMangaItemAdapter, mediaList)
            }
            mostRatedMangaListLiveData.observe(viewLifecycleOwner) { mediaList ->
                updateMediaList(mostRatedMangaItemAdapter, mediaList)
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

        fun onMediaOverviewCategoryMoreClick(
            sortTypes: List<MediaSortType>,
            mediaType: MediaType,
            status: MediaStatus?
        )

        fun onMediaOverviewMediaClick(media: Media)
    }

    companion object {

        fun createFragment(): MediaOverviewFragment {
            return MediaOverviewFragment()
        }
    }
}
