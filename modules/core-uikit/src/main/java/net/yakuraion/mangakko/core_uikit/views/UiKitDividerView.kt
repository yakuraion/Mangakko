package net.yakuraion.mangakko.core_uikit.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import net.yakuraion.mangakko.core_uikit.R
import net.yakuraion.mangakko.core_uikit.dpToPx
import net.yakuraion.mangakko.core_uikit.resolveColorAttr
import kotlin.math.roundToInt

class UiKitDividerView : View {

    init {
        setBackgroundColor(context.resolveColorAttr(R.attr.uikit_dividerColor))
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), HEIGHT)
    }

    companion object {

        private val HEIGHT = 0.5f.dpToPx().roundToInt()
    }
}
