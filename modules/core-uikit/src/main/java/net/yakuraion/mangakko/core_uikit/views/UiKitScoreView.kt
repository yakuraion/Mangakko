package net.yakuraion.mangakko.core_uikit.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatTextView
import net.yakuraion.mangakko.core_uikit.R
import net.yakuraion.mangakko.core_uikit.dpToPxInt
import net.yakuraion.mangakko.core_uikit.resolveColorAttr

class UiKitScoreView : AppCompatTextView {

    init {
        minimumWidth = SIZE_DP.dpToPxInt()
        minimumHeight = SIZE_DP.dpToPxInt()
        gravity = Gravity.CENTER
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.uikit_scoreViewStyle
    ) {
        attrs?.let { resolveAttrs(it) }
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        attrs?.let { resolveAttrs(it) }
    }

    private fun resolveAttrs(attrs: AttributeSet) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.UiKitScoreView)
        setScore(ta.getInt(R.styleable.UiKitScoreView_uikit_score, 0))
        ta.recycle()
    }

    fun setScore(score: Int) {
        text = score.toString()
        updateBackground(score)
        updateTextColor(score)
    }

    private fun updateBackground(score: Int) {
        val res = when {
            score >= GREEN_MIN -> R.drawable.uikit_score_view_bg_green
            score >= AMBER_MIN -> R.drawable.uikit_score_view_bg_amber
            else -> R.drawable.uikit_score_view_bg_red
        }
        setBackgroundResource(res)
    }

    private fun updateTextColor(score: Int) {
        val attrRes = when {
            score >= GREEN_MIN -> R.attr.uikit_green
            score >= AMBER_MIN -> R.attr.uikit_amber
            else -> R.attr.uikit_red
        }
        setTextColor(context.resolveColorAttr(attrRes))
    }

    companion object {

        private const val SIZE_DP = 40f

        private const val GREEN_MIN = 80
        private const val AMBER_MIN = 60
    }
}
