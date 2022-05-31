package ru.myproject.gamenewsapp.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.myproject.gameNewsAPP.R
import ru.myproject.gamenewsapp.network.api.GamesRemoteDataSource
import ru.myproject.gamenewsapp.network.api.params.GamesApiParams
import ru.myproject.gamenewsapp.repository.model.CategoryType
import ru.myproject.gamenewsapp.repository.model.GameCategoryModel
import ru.myproject.gamenewsapp.util.ResourceProvider
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class LatestReleasesGamesRepositoryImpl @Inject constructor(
  private val dataSource: GamesRemoteDataSource,
  private val resources: ResourceProvider
) : GameCategoryRepository {

  override fun data(): Flow<GameCategoryModel> = dataSource.observe()
    .map {  GameCategoryModel(
      title = resources.string(R.string.latest_releases),
      category=CategoryType.LatestReleases,
      dataState = it
    ) }

  override suspend fun init() {
    dataSource.initialLoading(
      GamesApiParams(
        dates = "2022-04-25,2022-05-25"))
  }
}