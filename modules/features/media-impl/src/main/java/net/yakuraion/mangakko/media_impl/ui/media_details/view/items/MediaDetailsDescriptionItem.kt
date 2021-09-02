package net.yakuraion.mangakko.media_impl.ui.media_details.view.items

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.media_item_media_details_description.view.descriptionTextView
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.ui.media_details.view.items.MediaDetailsDescriptionItem.ViewHolder

class MediaDetailsDescriptionItem(private val description: String) : AbstractItem<ViewHolder>() {

    override val layoutRes: Int = R.layout.media_item_media_details_description

    override val type: Int = layoutRes

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)
        holder.itemView.descriptionTextView.text = description
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
