package ru.myproject.gamenewsapp.adapters.main

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.myproject.gamenewsapp.adapters.base.BaseUtilItemCallback
import ru.myproject.gamenewsapp.adapters.main.MainScreenDelegates
import ru.myproject.gamenewsapp.adapters.main.MainScreenDelegates.gamesHorizontalDelegate
import ru.myproject.gamenewsapp.model.base.ListItem

class MainScreenAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseUtilItemCallback()) {
  init {
    delegatesManager.addDelegate(gamesHorizontalDelegate)
  }
}