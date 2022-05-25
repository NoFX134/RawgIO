package ru.myproject.gamenewsapp.network.model.base

import com.google.gson.annotations.SerializedName
import ru.myproject.gamenewsapp.network.model.GameDTO

data class PagedResponse (
  @SerializedName("count") val count: Int,
  @SerializedName("next") val nextPageUrl: String,
  @SerializedName("previous") val previousPageUrl: String,
  @SerializedName("results") val result: List<GameDTO>
    )