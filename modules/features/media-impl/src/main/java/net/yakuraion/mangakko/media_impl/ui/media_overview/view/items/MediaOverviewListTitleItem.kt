package net.yakuraion.mangakko.media_impl.ui.media_overview.view.items

import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.mikepenz.fastadapter.listeners.CustomEventHook
import kotlinx.android.synthetic.main.media_item_media_overview_list_title.view.moreTextView
import kotlinx.android.synthetic.main.media_item_media_overview_list_title.view.titleTextView
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.MOST_POPULAR_ANIME
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.MOST_POPULAR_MANGA
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.MOST_RATED_ANIME
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.MOST_RATED_MANGA
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.ONGOING_ANIME
import net.yakuraion.mangakko.media_impl.ui.media_overview.MediaOverviewCategory.ONGOING_MANGA
import net.yakuraion.mangakko.media_impl.ui.media_overview.view.items.MediaOverviewListTitleItem.ViewHolder

class MediaOverviewListTitleItem(private val category: MediaOverviewCategory) : AbstractItem<ViewHolder>() {

    override val layoutRes: Int = R.layout.media_item_media_overview_list_title

    override val type: Int = layoutRes

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)
        holder.itemView.titleTextView.setText(category.getTitleRes())
    }

    @StringRes
    private fun MediaOverviewCategory.getTitleRes(): Int {
        return when (this) {
            ONGOING_ANIME -> R.string.media_overview_category_ongoing_anime
            MOST_POPULAR_ANIME -> R.string.media_overview_category_most_popular_anime
            MOST_RATED_ANIME -> R.string.media_overview_category_most_rated_anime
            ONGOING_MANGA -> R.string.media_overview_category_ongoing_manga
            MOST_POPULAR_MANGA -> R.string.media_overview_category_most_popular_manga
            MOST_RATED_MANGA -> R.string.media_overview_category_most_rated_manga
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class MoreClickEventHook(
        private val onMoreClick: (category: MediaOverviewCategory) -> Unit
    ) : CustomEventHook<MediaOverviewListTitleItem>() {

        override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
            return (viewHolder as? ViewHolder)?.itemView
        }

        override fun attachEvent(view: View, viewHolder: RecyclerView.ViewHolder) {
            view.moreTextView.setOnClickListener {
                FastAdapter.getHolderAdapterItem<MediaOverviewListTitleItem>(viewHolder)?.let { item ->
                    onMoreClick.invoke(item.category)
                }
            }
        }
    }
}
