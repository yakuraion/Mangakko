package net.yakuraion.mangakko.core_feature.ui.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.yakuraion.mangakko.core_feature.di.viewmodel.InjectingSavedStateViewModelFactory
import kotlin.reflect.KClass

abstract class MVVMBottomSheetDialogFragment<VM : ViewModel>(
    private val viewModelClass: KClass<out ViewModel>,
    @LayoutRes private val layoutRes: Int
) : BottomSheetDialogFragment() {

    abstract val abstractViewModelFactory: InjectingSavedStateViewModelFactory

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = abstractViewModelFactory.create(viewModelClass, this, getDefaultArgs())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    protected open fun getDefaultArgs(): Bundle? = arguments
}
