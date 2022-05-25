package ru.myproject.gamenewsapp.model.game

import ru.myproject.gamenewsapp.model.base.ListItem


data class GamesHorizontalItem(
  val title: String,
  val games: List<ListItem>,

): ListItem{
  override val itemId: Long = title.hashCode().toLong()
}