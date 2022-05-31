package ru.myproject.gamenewsapp.repository.model

sealed class CategoryType{
  object TopUpcoming: CategoryType()
  object LatestReleases: CategoryType()
  object Rated: CategoryType()
  data class Genre(val id: Long): CategoryType()
}
