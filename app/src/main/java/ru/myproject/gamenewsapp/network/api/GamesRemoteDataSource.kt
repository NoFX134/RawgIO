package ru.myproject.gamenewsapp.network.api

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import ru.myproject.gamenewsapp.network.api.params.GamesApiParams
import ru.myproject.gamenewsapp.network.api.params.PagingState
import ru.myproject.gamenewsapp.network.model.GameDTO
import javax.inject.Inject


@FlowPreview
@ExperimentalCoroutinesApi
class GamesRemoteDataSource @Inject constructor(
  private val api: RawgApi
) {

  private val channel = ConflatedBroadcastChannel<PagingState<List<GameDTO>>>(PagingState.Initial)

  suspend fun initialLoading(params: GamesApiParams) {

    if (channel.value is PagingState.Initial) {
      val response = api.games(params.toMap())
      channel.send(PagingState.Content(response.result))
    }
  }

  suspend fun loadMore() {
//TODO
  }

  fun observe(): Flow<PagingState<List<GameDTO>>> = channel.asFlow()
}
