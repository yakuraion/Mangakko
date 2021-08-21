package net.yakuraion.mangakko.main_impl.ui.main.view

import android.content.Context
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import net.yakuraion.mangakko.core_feature.ui.base.BaseFragment
import net.yakuraion.mangakko.main_impl.di.injector
import net.yakuraion.mangakko.main_impl.ui.main.viewmodel.MainViewModel
import net.yakuraion.mangakko_impl.R
import javax.inject.Inject

class MainFragment : BaseFragment<MainViewModel>(MainViewModel::class, R.layout.main_fragment_main) {

    @Inject
    override lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    companion object {

        fun createFragment(): MainFragment {
            return MainFragment()
        }
    }
}
