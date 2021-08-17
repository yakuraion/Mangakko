package net.yakuraion.mangakko.core_ui

import androidx.viewpager.widget.ViewPager

fun ViewPager.onPageChanged(action: (position: Int) -> Unit): ViewPager.OnPageChangeListener {
    val listener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            // empty
        }

        override fun onPageSelected(position: Int) {
            action.invoke(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
            // empty
        }
    }
    addOnPageChangeListener(listener)
    return listener
}
