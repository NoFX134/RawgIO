package ru.myproject.gamenewsapp.ui.main

import ru.myproject.gamenewsapp.ui.base.ListItem


data class GamesHorizontalItem(
  val title: String,
  val games: List<ListItem>
): ListItem