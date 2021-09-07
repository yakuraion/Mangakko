package net.yakuraion.mangakko.genres_impl.ui.genres.view

import android.content.Context
import android.os.Bundle
import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.genres_fragment_genres.recyclerView
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.core_uikit.fragment.requireListener
import net.yakuraion.mangakko.genres.GenresFeature
import net.yakuraion.mangakko.genres_impl.R
import net.yakuraion.mangakko.genres_impl.di.injector
import net.yakuraion.mangakko.genres_impl.ui.genres.view.items.GenreItem
import net.yakuraion.mangakko.genres_impl.ui.genres.viewmodel.GenresViewModel
import javax.inject.Inject

class GenresFragment : BaseFragment<GenresViewModel>(
    GenresViewModel::class,
    R.layout.genres_fragment_genres
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    private lateinit var featureOwner: GenresFeature.Owner

    private val itemAdapter: ItemAdapter<GenreItem> = ItemAdapter()
    private val adapter: FastAdapter<GenreItem> = FastAdapter.with(itemAdapter).apply {
        onClickListener = { _, _, item, _ ->
            featureOwner.onGenreChosen(item.model)
            true
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        featureOwner = requireListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        viewModel.apply {
            genresLiveData.observe(viewLifecycleOwner) { setGenres(it) }
        }
    }

    private fun setGenres(genres: List<String>) {
        val items = genres.map { GenreItem(it) }
        itemAdapter.set(items)
    }

    override fun onDestroyView() {
        recyclerView.adapter = null
        super.onDestroyView()
    }

    companion object {

        fun createFragment(): GenresFragment {
            return GenresFragment()
        }
    }
}
