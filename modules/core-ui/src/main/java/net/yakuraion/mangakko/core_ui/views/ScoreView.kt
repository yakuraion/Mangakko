package net.yakuraion.mangakko.core_ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatTextView
import net.yakuraion.mangakko.core_ui.R
import net.yakuraion.mangakko.core_ui.dpToPxInt

class ScoreView : AppCompatTextView {

    init {
        minimumWidth = SIZE_DP.dpToPxInt()
        minimumHeight = SIZE_DP.dpToPxInt()
        gravity = Gravity.CENTER
        setBackgroundResource(R.drawable.score_view_bg)
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.custom_score_view_style
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
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ScoreView)
        setScore(ta.getInt(R.styleable.ScoreView_custom_score, 0))
        ta.recycle()
    }

    fun setScore(score: Int) {
        text = score.toString()
    }

    companion object {

        private const val SIZE_DP = 40f
    }
}
