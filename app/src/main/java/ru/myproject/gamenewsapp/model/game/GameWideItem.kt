package ru.myproject.gamenewsapp.model.game

import ru.myproject.gamenewsapp.model.base.ListItem

data class GameWideItem(
  val id: Long,
  val title: String,
  val imageUrl: String
    ) : ListItem {
  override val itemId: Long = id
}