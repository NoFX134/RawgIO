package ru.myproject.gamenewsapp.interactor.main

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import ru.myproject.gamenewsapp.model.base.ListItem
import ru.myproject.gamenewsapp.model.game.*
import ru.myproject.gamenewsapp.network.api.GamesRemoteDataSource
import ru.myproject.gamenewsapp.network.api.RawgApi
import ru.myproject.gamenewsapp.network.api.params.PagingState
import ru.myproject.gamenewsapp.repository.GameCategoryRepository
import ru.myproject.gamenewsapp.repository.LatestReleasesGamesRepositoryImpl
import ru.myproject.gamenewsapp.repository.RatedGamesRepositoryImpl
import ru.myproject.gamenewsapp.repository.TopUpcomingGamesRepositoryImpl
import ru.myproject.gamenewsapp.repository.model.CategoryType
import ru.myproject.gamenewsapp.repository.model.GameCategoryModel
import ru.myproject.gamenewsapp.util.ResourceProvider
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class MainScreenInteractorImpl @Inject constructor(
  private val api: RawgApi,
  private val resources: ResourceProvider
) : MainScreenInteractor {

  private val topUpcomingGamesRepository: GameCategoryRepository = TopUpcomingGamesRepositoryImpl(
    GamesRemoteDataSource(api), resources
  )
  private val latestReleasesGamesRepository: GameCategoryRepository =
    LatestReleasesGamesRepositoryImpl(
      GamesRemoteDataSource(api), resources
    )
  private val ratedGamesRepositoryImpl: GameCategoryRepository = RatedGamesRepositoryImpl(
    GamesRemoteDataSource(api), resources
  )

  override fun data(): Flow<List<ListItem>> = combine(
    topUpcomingGamesRepository.data(),
    latestReleasesGamesRepository.data(),
    ratedGamesRepositoryImpl.data()
  ) { topUpComing, latest, rated ->
    listOf(
      mapToCategory(topUpComing, true),
      mapToCategory(latest),
      mapToCategory(rated, true)
    )
  }

  override suspend fun initCategory(category: CategoryType) {
    when(category){
      is CategoryType.TopUpcoming -> topUpcomingGamesRepository.init()
      is CategoryType.LatestReleases -> latestReleasesGamesRepository.init()
      is CategoryType.Rated -> ratedGamesRepositoryImpl.init()
    }
  }

  private fun mapToCategory(model: GameCategoryModel, wide: Boolean = false): ListItem =
    when (model.dataState) {
      is PagingState.Initial -> {
        GamesHorizontalItem(
          title = model.title,
          category = model.category,
          games = IntRange(1, 3).map { if (wide) ProgressWideItem else ProgressThinItem }
        )
      }

      is PagingState.Content -> {

        GamesHorizontalItem(
          title = model.title,
          category = model.category,
          games = model.dataState.data.map {
            if (wide) {
              GameWideItem(
                id = it.id,
                title = it.title,
                imageUrl = it.image
              )
            } else {
              GameThinItem(
                id = it.id,
                title = it.title,
                imageUrl = it.image
              )
            }
          }
        )
      }

      else -> throw IllegalArgumentException("Wrong paging state ${model.dataState}")
    }
}




