package ru.myproject.gamenewsapp.model.game

import ru.myproject.gamenewsapp.model.base.ListItem
import ru.myproject.gamenewsapp.repository.model.CategoryType


data class GamesHorizontalItem(
  val title: String,
  val category: CategoryType,
  val games: List<ListItem>,

): ListItem{
  override val itemId: Long = title.hashCode().toLong()
}