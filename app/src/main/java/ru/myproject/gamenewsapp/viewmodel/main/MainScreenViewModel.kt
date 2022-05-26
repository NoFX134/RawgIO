package ru.myproject.gamenewsapp.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.myproject.gameNewsAPP.R
import ru.myproject.gamenewsapp.model.base.ListItem
import ru.myproject.gamenewsapp.model.game.*
import ru.myproject.gamenewsapp.network.api.RawgApi
import ru.myproject.gamenewsapp.network.di.NetworkComponent
import ru.myproject.gamenewsapp.util.ResourceProvider
import ru.myproject.gamenewsapp.viewmodel.base.BaseViewModel
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
  private val resources: ResourceProvider,

) : BaseViewModel() {
  private val api = NetworkComponent.retrofit
  private val _data = MutableLiveData<List<ListItem>>()
  val data: LiveData<List<ListItem>> = _data

  init {
    viewModelScope.launch(Dispatchers.IO) {
      _data.postValue(getLoaders())
      val items = getItems()
      _data.postValue(items)
    }
  }

  private fun getLoaders(): List<ListItem> {
    return listOf(
      GamesHorizontalItem(
        title = resources.string(R.string.top_upcoming),
        games = IntRange(1, 2).map { ProgressWideItem }
      ),
      GamesHorizontalItem(
        title = resources.string(R.string.latest_releases),
        games = IntRange(1, 3).map { ProgressThinItem }
      ),
      GamesHorizontalItem(
        title = resources.string(R.string.rated_in_2022),
        games = IntRange(1, 20).map { ProgressWideItem }
      )
    )
  }

  private suspend fun getItems(): List<ListItem> {
    val topUpcomingResponse = api.games(
      mapOf(
        "dates" to "2022-05-25,2023-05-25",
        "ordering" to "-added"
      )
    )
    val latestReleasesResponse = api.games(
      mapOf(
        "dates" to "2022-04-25,2022-05-25"
      )
    )
    val mostRatedResponse = api.games(
      mapOf(
        "dates" to "2022-01-01,2022-05-25",
        "ordering" to "-rating"
      )
    )
    val topUpcoming = topUpcomingResponse.result.map {
      GameWideItem(
        id = it.id,
        title = it.title,
        imageUrl = it.image
      )
    }
    val latestReleases = latestReleasesResponse.result.map {
      GameThinItem(
        id = it.id,
        title = it.title,
        imageUrl = it.image
      )
    }
    val mostRated = mostRatedResponse.result.map {
      GameWideItem(
        id = it.id,
        title = it.title,
        imageUrl = it.image
      )
    }
    return listOf(
      GamesHorizontalItem(
        title = "Топ ожидаемых",
        games = topUpcoming
      ),
      GamesHorizontalItem(
        title = "Новинки",
        games = latestReleases
      ),
      GamesHorizontalItem(
        title = "Рейтинговые игры 2022",
        games = mostRated
      )
    )
  }
}
