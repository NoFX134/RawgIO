package ru.myproject.gamenewsapp

import android.app.Application
import ru.myproject.gamenewsapp.di.DaggerAppComponent
import ru.myproject.gamenewsapp.network.di.DaggerNetworkComponent

class App: Application() {

  override fun onCreate() {
    super.onCreate()
    initDi()

  }

  private fun initDi(){
    DI.appComponent=DaggerAppComponent.builder()
      .appContext(this)
      .build()

    DI.networkComponent = DaggerNetworkComponent.create()

  }
}
//  private suspend fun getItems(): List<ListItem> {
//    val topUpcomingResponse = api.games(
//      mapOf(
//        "dates" to "2022-05-25,2023-05-25",
//        "ordering" to "-added"
//      )
//    )
//    val latestReleasesResponse = api.games(
//      mapOf(
//        "dates" to "2022-04-25,2022-05-25"
//      )
//    )
//    val mostRatedResponse = api.games(
//      mapOf(
//        "dates" to "2022-01-01,2022-05-25",
//        "ordering" to "-rating"
//      )
//    )
//    val topUpcoming = topUpcomingResponse.result.map {
//      GameWideItem(
//        id = it.id,
//        title = it.title,
//        imageUrl = it.image
//      )
//    }
//    val latestReleases = latestReleasesResponse.result.map {
//      GameThinItem(
//        id = it.id,
//        title = it.title,
//        imageUrl = it.image
//      )
//    }
//    val mostRated = mostRatedResponse.result.map {
//      GameWideItem(
//        id = it.id,
//        title = it.title,
//        imageUrl = it.image
//      )
//    }
//    return listOf(
//      GamesHorizontalItem(
//        title = "Топ ожидаемых",
//        games = topUpcoming
//      ),
//      GamesHorizontalItem(
//        title = "Новинки",
//        games = latestReleases
//      ),
//      GamesHorizontalItem(
//        title = "Рейтинговые игры 2022",
//        games = mostRated
//      )
//    )
//  }