package ru.myproject.gamenewsapp.interactor.main

import kotlinx.coroutines.flow.Flow
import ru.myproject.gamenewsapp.model.base.ListItem
import ru.myproject.gamenewsapp.repository.model.CategoryType

interface MainScreenInteractor {

  fun data(): Flow<List<ListItem>>

  suspend fun initCategory(category: CategoryType)

}
