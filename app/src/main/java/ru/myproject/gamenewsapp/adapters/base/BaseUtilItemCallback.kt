package ru.myproject.gamenewsapp.adapters.base

import androidx.recyclerview.widget.DiffUtil
import ru.myproject.gamenewsapp.model.base.ListItem

open class BaseUtilItemCallback : DiffUtil.ItemCallback<ListItem>() {

  override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
    return oldItem.itemId == newItem.itemId
  }

  override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
    return oldItem.equals(newItem)
  }

}
