package ru.myproject.gamenewsapp.interactor.main

import kotlinx.coroutines.flow.Flow
import ru.myproject.gamenewsapp.model.base.ListItem

interface MainScreenInteractor {
  fun data(): Flow<List<ListItem>>
}