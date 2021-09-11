package net.yakuraion.mangakko.media_impl.ui.media_details.view.items

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.AbstractItem
import com.mikepenz.fastadapter.listeners.CustomEventHook
import kotlinx.android.synthetic.main.media_item_media_details_score_with_like.view.likeImageView
import kotlinx.android.synthetic.main.media_item_media_details_score_with_like.view.popularityAllTimeTextView
import kotlinx.android.synthetic.main.media_item_media_details_score_with_like.view.rateAllTimeTextView
import kotlinx.android.synthetic.main.media_item_media_details_score_with_like.view.scoreView
import net.yakuraion.mangakko.core_uikit.resolveColorAttr
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.ui.media_details.view.items.MediaDetailsScoreWithLikeItem.ViewHolder

class MediaDetailsScoreWithLikeItem : AbstractItem<ViewHolder>() {

    var score: Int? = null

    var rateRank: Int? = null
    var popularityRank: Int? = null

    var isFavorite: Boolean = false

    override val layoutRes: Int = R.layout.media_item_media_details_score_with_like

    override val type: Int = layoutRes

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)
        holder.apply {
            bindScore(score)
            bindRateRank(rateRank)
            bindPopularityRank(popularityRank)
            bindIsFavorite(isFavorite)
        }
    }

    private fun ViewHolder.bindScore(score: Int?) {
        itemView.scoreView.apply {
            isVisible = score != null
            score?.let { setScore(it) }
        }
    }

    private fun ViewHolder.bindRateRank(rank: Int?) {
        itemView.rateAllTimeTextView.apply {
            if (rank != null) {
                text = itemView.context.getString(R.string.media_details_highest_score_all_time, rank)
                setTextColor(context.resolveColorAttr(R.attr.uikit_textColor))
                val drawableColor = context.resolveColorAttr(R.attr.uikit_accentColor)
                val colorStateList = ColorStateList.valueOf(drawableColor)
                TextViewCompat.setCompoundDrawableTintList(this, colorStateList)
            } else {
                text = context.getString(R.string.media_details_highest_score_all_time_not_available)
                setTextColor(context.resolveColorAttr(R.attr.uikit_textColorDisabled))
                val color = ContextCompat.getColor(context, R.color.uikit_grey_50_100)
                TextViewCompat.setCompoundDrawableTintList(this, ColorStateList.valueOf(color))
            }
        }
    }

    private fun ViewHolder.bindPopularityRank(rank: Int?) {
        itemView.popularityAllTimeTextView.apply {
            if (rank != null) {
                text = itemView.context.getString(R.string.media_details_most_popular_all_time, rank)
                setTextColor(context.resolveColorAttr(R.attr.uikit_textColor))
                val drawableColor = ContextCompat.getColor(context, R.color.uikit_red)
                val colorStateList = ColorStateList.valueOf(drawableColor)
                TextViewCompat.setCompoundDrawableTintList(this, colorStateList)
            } else {
                text = context.getString(R.string.media_details_most_popular_all_time_not_available)
                setTextColor(context.resolveColorAttr(R.attr.uikit_textColorDisabled))
                val color = ContextCompat.getColor(context, R.color.uikit_grey_50_100)
                TextViewCompat.setCompoundDrawableTintList(this, ColorStateList.valueOf(color))
            }
        }
    }

    private fun ViewHolder.bindIsFavorite(isFavorite: Boolean) {
        val (iconRes, colorRes) = if (isFavorite) {
            R.drawable.uikit_ic_favorite_24 to R.color.uikit_red
        } else {
            R.drawable.uikit_ic_favorite_border_24 to R.color.uikit_grey_50_100
        }
        itemView.likeImageView.apply {
            setImageResource(iconRes)
            imageTintList = ColorStateList.valueOf(ContextCompat.getColor(itemView.context, colorRes))
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class LikeClickEventHook(
        private val action: () -> Unit
    ) : CustomEventHook<MediaDetailsScoreWithLikeItem>() {

        override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
            return (viewHolder as? ViewHolder)?.itemView
        }

        override fun attachEvent(view: View, viewHolder: RecyclerView.ViewHolder) {
            view.likeImageView.setOnClickListener { action.invoke() }
        }
    }
}
