package ru.myproject.gamenewsapp.interactor.main

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import ru.myproject.gamenewsapp.model.base.ListItem
import ru.myproject.gamenewsapp.model.game.GameWideItem
import ru.myproject.gamenewsapp.model.game.GamesHorizontalItem
import ru.myproject.gamenewsapp.model.game.ProgressWideItem
import ru.myproject.gamenewsapp.network.api.GamesRemoteDataSource
import ru.myproject.gamenewsapp.network.api.params.PagingState
import ru.myproject.gamenewsapp.network.model.GameDTO
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class MainScreenInteractorImpl @Inject constructor(
  private val topUpComingDataSource: GamesRemoteDataSource,
  private val latestReleasesDataSource: GamesRemoteDataSource,
  private val ratedDataSource: GamesRemoteDataSource
) : MainScreenInteractor {

  override fun data(): Flow<List<ListItem>> = combine(
    topUpComingDataSource.observe(),
    latestReleasesDataSource.observe(),
    ratedDataSource.observe()
  ) { topUpComing, latest, rated ->
    listOf(
      mapToCategory(topUpComing),
      mapToCategory(latest),
      mapToCategory(rated)
    )
  }

  private fun mapToCategory(state: PagingState<List<GameDTO>>): ListItem =
    when (state) {
      is PagingState.Initial -> {
        GamesHorizontalItem(
          title = "TODO TITLE",
          games = IntRange(1, 2).map { ProgressWideItem }
        )
      }
      is PagingState.Content -> {
        GamesHorizontalItem(
          title = "TODO TITLE",
          games=state.data.map {
            GameWideItem(
              id = it.id,
              title = it.title,
              imageUrl = it.image
            )
          }
        )
      }
      else -> throw IllegalArgumentException("Wrong paging state $state")
    }
  }




