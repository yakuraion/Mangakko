package net.yakuraion.mangakko.media_impl.ui.common

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.mikepenz.fastadapter.items.ModelAbstractItem
import kotlinx.android.synthetic.main.media_item_media.view.imageView
import kotlinx.android.synthetic.main.media_item_media.view.scoreView
import kotlinx.android.synthetic.main.media_item_media.view.shimmerFrameLayout
import kotlinx.android.synthetic.main.media_item_media.view.titleTextView
import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.media_impl.R
import net.yakuraion.mangakko.media_impl.ui.common.MediaItem.ViewHolder

class MediaItem(model: Media?) : ModelAbstractItem<Media?, ViewHolder>(model) {

    override val layoutRes: Int = R.layout.media_item_media

    override val type: Int = layoutRes

    private var glideRequestManager: RequestManager? = null

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)
        glideRequestManager = Glide.with(holder.itemView)
        model?.let { holder.bindWithModel(it) } ?: holder.bindWithPlaceholder()
    }

    private fun ViewHolder.bindWithModel(model: Media) {
        itemView.shimmerFrameLayout.hideShimmer()
        bindImage(model.imageUrl)
        bindTitle(model.title)
        bindScore(model.score)
    }

    private fun ViewHolder.bindImage(imageUrl: String) {
        glideRequestManager
            ?.load(imageUrl)
            ?.into(itemView.imageView)
    }

    private fun ViewHolder.bindTitle(title: String) {
        itemView.titleTextView.apply {
            isVisible = true
            text = title
        }
    }

    private fun ViewHolder.bindScore(score: Int?) {
        itemView.scoreView.apply {
            isVisible = score != null
            score?.let { setScore(it) }
        }
    }

    private fun ViewHolder.bindWithPlaceholder() {
        itemView.apply {
            shimmerFrameLayout.showShimmer(true)
            titleTextView.isVisible = false
            scoreView.isVisible = false
        }
    }

    override fun unbindView(holder: ViewHolder) {
        glideRequestManager?.clear(holder.itemView.imageView)
        glideRequestManager = null
        super.unbindView(holder)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
