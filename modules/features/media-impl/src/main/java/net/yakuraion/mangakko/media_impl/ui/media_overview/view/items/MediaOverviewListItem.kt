package net.yakuraion.mangakko.media_impl.ui.media_overview.view.items

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.mikepenz.fastadapter.listeners.CustomEventHook
import kotlinx.android.synthetic.main.media_item_media_overview_list.view.nestedItemsRecyclerView
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_uikit.dpToPxInt
import net.yakuraion.mangakko.core_uikit.fastadapter.items.media.MediaItem
import net.yakuraion.mangakko.core_uikit.itemdecorator.setItemMargins
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.ui.media_overview.view.items.MediaOverviewListItem.ViewHolder

class MediaOverviewListItem(private val mediaList: List<Media?>) : AbstractItem<ViewHolder>() {

    override val layoutRes: Int = R.layout.media_item_media_overview_list

    override val type: Int = layoutRes

    override fun getViewHolder(v: View): ViewHolder {
        val itemAdapter = ItemAdapter<MediaItem>()
        v.nestedItemsRecyclerView.apply {
            adapter = FastAdapter.with(itemAdapter)
            setItemMargins(
                NESTED_RECYCLER_VIEW_PADDING_DP.dpToPxInt(),
                NESTED_RECYCLER_VIEW_PADDING_DP.dpToPxInt()
            )
        }
        return ViewHolder(v)
    }

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)
        val items = mediaList.map { MediaItem(it) }
        holder.getItemAdapter().set(items)
    }

    override fun unbindView(holder: ViewHolder) {
        holder.getItemAdapter().set(emptyList())
    }

    @Suppress("UNCHECKED_CAST")
    private fun ViewHolder.getItemAdapter(): ItemAdapter<MediaItem> {
        return (itemView.nestedItemsRecyclerView.adapter as FastAdapter<MediaItem>)
            .adapter(0) as ItemAdapter<MediaItem>
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class NestedMediaClickEventHook(
        private val onMediaClick: (media: Media) -> Unit
    ) : CustomEventHook<MediaOverviewListItem>() {

        override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
            return (viewHolder as? ViewHolder)?.itemView
        }

        override fun attachEvent(view: View, viewHolder: RecyclerView.ViewHolder) {
            (view.nestedItemsRecyclerView.adapter as? FastAdapter<*>)?.onClickListener = { _, _, item, _ ->
                (item as? MediaItem)?.model?.let(onMediaClick)
                true
            }
        }
    }

    companion object {

        private const val NESTED_RECYCLER_VIEW_PADDING_DP = 8f
    }
}
