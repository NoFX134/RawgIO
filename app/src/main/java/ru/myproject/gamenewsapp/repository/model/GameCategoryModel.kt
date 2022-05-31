package ru.myproject.gamenewsapp.repository.model

import ru.myproject.gamenewsapp.network.api.params.PagingState
import ru.myproject.gamenewsapp.network.model.GameDTO

data class GameCategoryModel(
  val title: String,
  val category: CategoryType,
  val dataState: PagingState<List<GameDTO>>
) {
}