package net.yakuraion.mangakko.favorites_impl.ui.favorites_list.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.favorites_fragment_favorites_list.recyclerView
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.core_uikit.dpToPxInt
import net.yakuraion.mangakko.core_uikit.fastadapter.items.media.MediaItem
import net.yakuraion.mangakko.core_uikit.fragment.requireListener
import net.yakuraion.mangakko.core_uikit.itemdecorator.setItemMargins
import net.yakuraion.mangakko.favorites_impl.R
import net.yakuraion.mangakko.favorites_impl.di.injector
import net.yakuraion.mangakko.favorites_impl.ui.favorites_list.viewmodel.FavoritesListViewModel
import javax.inject.Inject

class FavoritesListFragment : BaseFragment<FavoritesListViewModel>(
    FavoritesListViewModel::class,
    R.layout.favorites_fragment_favorites_list
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    private lateinit var listener: Listener

    private val itemAdapter: ItemAdapter<MediaItem> = ItemAdapter()

    private val adapter: FastAdapter<MediaItem> = FastAdapter.with(itemAdapter).apply {
        onClickListener = { _, _, item, _ ->
            item.model?.let { listener.onFavoritesListMediaClick(it) }
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
            mediaListLiveData.observe(viewLifecycleOwner) { updateMediaList(it) }
        }
    }

    private fun setUpRecyclerView() {
        recyclerView.apply {
            adapter = this@FavoritesListFragment.adapter
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

    private fun updateMediaList(list: List<Media?>) {
        val items = list.map { MediaItem(it) }
        itemAdapter.set(items)
    }

    override fun onDestroyView() {
        recyclerView.adapter = null
        super.onDestroyView()
    }

    interface Listener {

        fun onFavoritesListMediaClick(media: Media)
    }

    companion object {

        private const val RECYCLER_VIEW_PADDING_DP = 8f

        fun createFragment(): FavoritesListFragment {
            return FavoritesListFragment()
        }
    }
}
