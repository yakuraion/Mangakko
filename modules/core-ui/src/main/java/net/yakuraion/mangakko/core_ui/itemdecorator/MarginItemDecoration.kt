package net.yakuraion.mangakko.core_ui.itemdecorator

import android.graphics.Rect
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * [RecyclerView.ItemDecoration] to add margins to items
 *
 * Note: Not working with [GridLayoutManager] with different item span size
 * Note: Not working with reverse layout and stackFromEnd
 *
 *
 *   Left                   Horizontal                Right
 *     |                        |                       |
 *     V                        V                       V
 *
 *                                                          <- Top
 *
 *          Item        Item        Item        Item
 *
 *          Item        Item        Item        Item
 *                                                          <- Vertical
 *          Item        Item        Item        Item
 *
 *          Item        Item        Item        Item
 *
 *                                                          <- Bottom
 *
 */
class MarginItemDecoration(
    private val vertical: Int,
    private val horizontal: Int,
    private val left: Int,
    private val top: Int,
    private val right: Int,
    private val bottom: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val layoutManager = parent.layoutManager
        val position = parent.getChildAdapterPosition(view)
        when (layoutManager) {
            is GridLayoutManager -> {
                getItemOffsetsForGridLayout(
                    outRect,
                    position,
                    layoutManager.orientation,
                    layoutManager.spanCount,
                    parent.adapter?.itemCount ?: 0
                )
            }
            is LinearLayoutManager -> {
                getItemOffsetsForGridLayout(
                    outRect,
                    position,
                    layoutManager.orientation,
                    1,
                    parent.adapter?.itemCount ?: 0
                )
            }
        }
    }

    private fun getItemOffsetsForGridLayout(
        outRect: Rect,
        position: Int,
        orientation: Int,
        spanCount: Int,
        childCount: Int
    ) {
        val rect = if (orientation == LinearLayout.VERTICAL) {
            getItemOffsetsRectForVertical(
                position, spanCount, childCount, vertical, horizontal, left, top, right, bottom
            )
        } else {
            getItemOffsetsRectForVertical(
                position, spanCount, childCount, horizontal, vertical, top, right, bottom, left
            ).rotateToLeft90()
        }
        outRect.set(rect)
    }

    private fun getItemOffsetsRectForVertical(
        position: Int,
        spanCount: Int,
        childCount: Int,
        vertical: Int,
        horizontal: Int,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ): Rect {
        val isOnTopRow = position < spanCount
        val itemsInBottomRow = if (childCount % spanCount == 0) spanCount else childCount % spanCount
        val isOnBottomRow = position >= childCount - itemsInBottomRow
        val isOnRightRow = (position + 1) % spanCount == 0
        val isOnLeftRow = position % spanCount == 0
        return Rect().apply {
            this.right = if (isOnRightRow) right else horizontal / 2
            this.left = if (isOnLeftRow) left else horizontal / 2
            this.bottom = if (isOnBottomRow) bottom else vertical / 2
            this.top = if (isOnTopRow) top else vertical / 2
        }
    }

    private fun Rect.rotateToLeft90(): Rect {
        return Rect().apply {
            left = this@rotateToLeft90.top
            top = this@rotateToLeft90.right
            right = this@rotateToLeft90.bottom
            bottom = this@rotateToLeft90.left
        }
    }
}

fun RecyclerView.setItemMargins(vertical: Int, horizontal: Int) {
    val itemDecoration = MarginItemDecoration(
        vertical = vertical,
        horizontal = horizontal,
        left = horizontal,
        top = vertical,
        right = horizontal,
        bottom = vertical
    )
    addItemDecoration(itemDecoration)
}

fun RecyclerView.setItemMargins(vertical: Int, horizontal: Int, left: Int, top: Int, right: Int, bottom: Int) {
    val itemDecoration = MarginItemDecoration(
        vertical = vertical,
        horizontal = horizontal,
        left = left,
        top = top,
        right = right,
        bottom = bottom
    )
    addItemDecoration(itemDecoration)
}
