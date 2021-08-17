package net.yakuraion.mangakko.core_feature.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import net.yakuraion.mangakko.core_feature.ui.mvvm.MVVMFragment
import kotlin.reflect.KClass

abstract class BaseFragment<VM : BaseViewModel>(
    viewModelClass: KClass<out ViewModel>,
    @LayoutRes layoutRes: Int
) : MVVMFragment<VM>(viewModelClass, layoutRes) {

    constructor(viewModelClass: KClass<out ViewModel>) : this(viewModelClass, 0)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorLiveData.observe(viewLifecycleOwner) { throwable ->
            // todo add default error listener
        }
    }
}
