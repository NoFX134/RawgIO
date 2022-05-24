package ru.myproject.gamenewsapp.adapters

import ru.myproject.gamenewsapp.ui.base.ListItem


data class GamesHorizontalItem(
  val title: String,
  val games: List<ListItem>
): ListItem