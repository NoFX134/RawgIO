package ru.myproject.gamenewsapp.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.myproject.gamenewsapp.adapters.GamesHorizontalItem
import ru.myproject.gamenewsapp.ui.base.ListItem
import ru.myproject.gamenewsapp.ui.main.GameThinItem
import ru.myproject.gamenewsapp.ui.main.GameWideItem
import ru.myproject.gamenewsapp.viewmodel.base.BaseViewModel

class MainScreenViewModel : BaseViewModel() {
  private val _data = MutableLiveData<List<ListItem>>()
  val data: LiveData<List<ListItem>> = _data

  init {
    viewModelScope.launch {
      val items = getItems()
      _data.postValue(items)
    }


  }

  private suspend fun getItems(): List<ListItem> {
    return listOf(
      GamesHorizontalItem(
        title = "Wide Games Title",
        games = IntRange(1, 20).map { GameWideItem(it.toLong(), "Game Title $it") }
      ),
      GamesHorizontalItem(
        title = "Thin Games Title",
        games = IntRange(1, 20).map { GameThinItem(it.toLong(), "Game Title $it") }
      ),
      GamesHorizontalItem(
        title = "Wide Games Title",
        games = IntRange(1, 20).map { GameWideItem(it.toLong(), "Game Title $it") }
      )
    )
  }

  override fun onCleared() {

    super.onCleared()
  }
}