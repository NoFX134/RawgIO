package ru.myproject.gamenewsapp.adapters.main

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.myproject.gamenewsapp.adapters.base.BaseUtilItemCallback
import ru.myproject.gamenewsapp.adapters.main.MainScreenDelegates.gamesHorizontalDelegate
import ru.myproject.gamenewsapp.model.base.ListItem
import ru.myproject.gamenewsapp.model.game.GamesHorizontalItem

class MainScreenAdapter(onItemBind:(GamesHorizontalItem)->Unit) : AsyncListDifferDelegationAdapter<ListItem>(BaseUtilItemCallback()) {
  init {
    delegatesManager.addDelegate(
      gamesHorizontalDelegate(
      onItemBind = onItemBind
    ))
  }
}