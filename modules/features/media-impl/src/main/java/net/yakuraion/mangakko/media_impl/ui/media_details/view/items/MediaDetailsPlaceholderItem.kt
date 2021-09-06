package net.yakuraion.mangakko.media_impl.ui.media_details.view.items

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.AbstractItem
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.ui.media_details.view.items.MediaDetailsPlaceholderItem.ViewHolder

class MediaDetailsPlaceholderItem : AbstractItem<ViewHolder>() {

    override val layoutRes: Int = R.layout.media_item_media_details_placeholder

    override val type: Int = layoutRes

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
