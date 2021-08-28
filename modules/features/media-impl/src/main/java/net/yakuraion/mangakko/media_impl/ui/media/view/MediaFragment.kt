package net.yakuraion.mangakko.media_impl.ui.media.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.AsyncDifferConfig
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.paged.PagedModelAdapter
import kotlinx.android.synthetic.main.media_fragment_media.recyclerView
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.di.injector
import net.yakuraion.mangakko.media_impl.ui.media.view.items.MediaDiffUtilItemCallback
import net.yakuraion.mangakko.media_impl.ui.media.view.items.MediaItem
import net.yakuraion.mangakko.media_impl.ui.media.viewmodel.MediaViewModel
import net.yakuraion.mangakko.media_impl.ui.media.viewmodel.MediaViewModel.Companion.ARG_GENRE
import javax.inject.Inject

@Suppress("EXPERIMENTAL_API_USAGE")
class MediaFragment : BaseFragment<MediaViewModel>(
    MediaViewModel::class,
    R.layout.media_fragment_media
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    private val itemAdapter: PagedModelAdapter<Media, MediaItem> = PagedModelAdapter(
        AsyncDifferConfig.Builder(MediaDiffUtilItemCallback()).build(),
        { MediaItem(null) }
    ) { MediaItem(it) }

    private val adapter: FastAdapter<MediaItem> = FastAdapter.with(itemAdapter)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        viewModel.apply {
            mediaPagedListLiveData.observe(viewLifecycleOwner) { itemAdapter.submitList(it) }
        }
    }

    override fun onDestroyView() {
        recyclerView.adapter = null
        super.onDestroyView()
    }

    companion object {

        fun createFragment(genre: String): MediaFragment {
            return MediaFragment().apply {
                arguments = bundleOf(
                    ARG_GENRE to genre
                )
            }
        }
    }
}
