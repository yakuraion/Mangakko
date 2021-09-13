package net.yakuraion.mangakko.core_feature.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import net.yakuraion.mangakko.core_feature.ui.mvvm.MVVMDialogFragment
import kotlin.reflect.KClass

/**
 * Базовый [DialogFragment], от которой должны наследоваться все [DialogFragment] в приложении
 */
abstract class BaseDialogFragment<VM : BaseViewModel>(
    viewModelClass: KClass<out ViewModel>,
    @LayoutRes layoutRes: Int
) : MVVMDialogFragment<VM>(viewModelClass, layoutRes) {

    constructor(viewModelClass: KClass<out ViewModel>) : this(viewModelClass, 0)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireContext(), theme) {
            override fun onBackPressed() {
                handleBackPressed()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorLiveData.observe(viewLifecycleOwner) { throwable ->
            // todo add default error listener
        }
    }

    protected open fun handleBackPressed() {
        dismiss()
    }
}
