package net.yakuraion.mangakko.pager_impl.pager.view

import android.content.Context
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.media.MediaFeature
import net.yakuraion.mangakko.pager_impl.R
import net.yakuraion.mangakko.pager_impl.di.injector
import net.yakuraion.mangakko.pager_impl.pager.viewmodel.PagerViewModel
import javax.inject.Inject

class PagerFragment : BaseFragment<PagerViewModel>(
    PagerViewModel::class,
    R.layout.pager_fragment_pager
) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    @Inject
    lateinit var mediaFeature: MediaFeature

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    companion object {

        fun createFragment(): PagerFragment {
            return PagerFragment()
        }
    }
}
