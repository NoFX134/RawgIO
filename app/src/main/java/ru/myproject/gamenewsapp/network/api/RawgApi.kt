package ru.myproject.gamenewsapp.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.myproject.gamenewsapp.network.model.base.PagedResponse
import ru.myproject.gamenewsapp.util.Constants.API_KEY

interface RawgApi {

  @GET("/api/games")
  suspend fun games(
    @QueryMap params: Map<String, String>,
    @Query("key") key: String = API_KEY
  ): PagedResponse

}