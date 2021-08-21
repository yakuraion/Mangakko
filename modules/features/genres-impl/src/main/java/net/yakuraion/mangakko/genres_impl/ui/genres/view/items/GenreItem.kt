package net.yakuraion.mangakko.genres_impl.ui.genres.view.items

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.ModelAbstractItem
import kotlinx.android.synthetic.main.genres_item_genre.view.*
import net.yakuraion.mangakko.genres_impl.R

class GenreItem(genre: String) : ModelAbstractItem<String, GenreItem.ViewHolder>(genre) {

    override val layoutRes: Int = R.layout.genres_item_genre

    override val type: Int = layoutRes

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)
        holder.itemView.apply {
            titleTextView.text = model
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
