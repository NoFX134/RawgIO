package ru.myproject.gamenewsapp.repository

import kotlinx.coroutines.flow.Flow
import ru.myproject.gamenewsapp.repository.model.GameCategoryModel

interface GameCategoryRepository {

  fun data(): Flow<GameCategoryModel>

  suspend fun init()

}