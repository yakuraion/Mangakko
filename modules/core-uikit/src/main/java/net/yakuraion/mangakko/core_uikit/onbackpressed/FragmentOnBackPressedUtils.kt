package net.yakuraion.mangakko.core_uikit.onbackpressed

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

/**
 * Очищать backStack фрагмента по каждому вызову onBackPressed до того момента, пока backStack не опустеет
 */
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
