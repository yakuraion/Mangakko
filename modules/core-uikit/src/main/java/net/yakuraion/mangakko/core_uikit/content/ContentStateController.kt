package net.yakuraion.mangakko.core_uikit.content

import android.view.View
import androidx.core.view.isVisible
import net.yakuraion.mangakko.core_uikit.content.ContentState.CONTENT
import net.yakuraion.mangakko.core_uikit.content.ContentState.EMPTY
import net.yakuraion.mangakko.core_uikit.content.ContentState.ERROR
import net.yakuraion.mangakko.core_uikit.content.ContentState.PROGRESS
import kotlin.properties.Delegates

/**
 * Контроллер, управляющий состоянием экрана
 *
 * Позволяет удобно определить какие [View] надо показывать при каждом конкретном состоянии экрана
 * (есть контент, загрузка, нет данных, ошибка)
 */
class ContentStateController(
    private val contentViewIds: List<Int> = emptyList(),
    private val progressViewIds: List<Int> = emptyList(),
    private val emptyViewIds: List<Int> = emptyList(),
    private val errorViewIds: List<Int> = emptyList(),
) {

    var state: ContentState? by Delegates.observable(null) { _, _, _ ->
        updateViews()
    }

    private var contentViews: List<View> = emptyList()
    private var progressViews: List<View> = emptyList()
    private var emptyViews: List<View> = emptyList()
    private var errorViews: List<View> = emptyList()

    fun attachView(view: View) {
        contentViews = contentViewIds.map { view.findViewById(it) }
        progressViews = progressViewIds.map { view.findViewById(it) }
        emptyViews = emptyViewIds.map { view.findViewById(it) }
        errorViews = errorViewIds.map { view.findViewById(it) }
    }

    fun detachView() {
        contentViews = emptyList()
        progressViews = emptyList()
        emptyViews = emptyList()
        errorViews = emptyList()
    }

    private fun updateViews() {
        when (state) {
            CONTENT -> showContent()
            PROGRESS -> showProgress()
            EMPTY -> showEmpty()
            ERROR -> showError()
        }
    }

    private fun showContent() {
        contentViews.forEach { it.isVisible = true }
        updateProgressViewsIsVisible(false)
        emptyViews.forEach { it.isVisible = false }
        errorViews.forEach { it.isVisible = false }
    }

    private fun showProgress() {
        contentViews.forEach { it.isVisible = false }
        updateProgressViewsIsVisible(true)
        emptyViews.forEach { it.isVisible = false }
        errorViews.forEach { it.isVisible = false }
    }

    private fun showEmpty() {
        contentViews.forEach { it.isVisible = false }
        updateProgressViewsIsVisible(false)
        emptyViews.forEach { it.isVisible = true }
        errorViews.forEach { it.isVisible = false }
    }

    private fun showError() {
        contentViews.forEach { it.isVisible = false }
        updateProgressViewsIsVisible(false)
        emptyViews.forEach { it.isVisible = false }
        errorViews.forEach { it.isVisible = true }
    }

    private fun updateProgressViewsIsVisible(isVisible: Boolean) {
        progressViews.forEach { it.isVisible = isVisible }
    }
}
