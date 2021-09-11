package net.yakuraion.mangakko.core_uikit.onbackpressed

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

fun Fragment.setUpOnBackPressedForClearBackStack(): OnBackPressedCallback {
    return requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), viewLifecycleOwner) {
        if (childFragmentManager.backStackEntryCount > 0) {
            childFragmentManager.popBackStack()
            true
        } else {
            false
        }
    }
}
