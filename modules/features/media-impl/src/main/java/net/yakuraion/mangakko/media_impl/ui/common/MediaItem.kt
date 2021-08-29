package net.yakuraion.mangakko.media_impl.ui.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.items.ModelAbstractItem
import kotlinx.android.synthetic.main.media_item_media.view.imageView
import kotlinx.android.synthetic.main.media_item_media.view.titleTextView
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.ui.common.MediaItem.ViewHolder

class MediaItem(model: Media?) : ModelAbstractItem<Media?, ViewHolder>(model) {

    override val layoutRes: Int = R.layout.media_item_media

    override val type: Int = layoutRes

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)
        model?.let { holder.bindWithModel(it) } ?: holder.bindWithPlaceholder()
    }

    private fun ViewHolder.bindWithModel(model: Media) {
        bindImage(model.imageUrl)
        itemView.titleTextView.text = model.title
    }

    private fun ViewHolder.bindImage(imageUrl: String) {
        Glide.with(itemView)
            .load(imageUrl)
            .into(itemView.imageView)
    }

    private fun ViewHolder.bindWithPlaceholder() {
        itemView.apply {
            imageView.setImageDrawable(null)
            titleTextView.text = "loading"
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
