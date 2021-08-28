package net.yakuraion.mangakko.media_impl.ui.media.view.items

import androidx.recyclerview.widget.DiffUtil
import net.yakuraion.mangakko.core_entity.Media

class MediaDiffUtilItemCallback : DiffUtil.ItemCallback<Media>() {

    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem == newItem
    }
}
