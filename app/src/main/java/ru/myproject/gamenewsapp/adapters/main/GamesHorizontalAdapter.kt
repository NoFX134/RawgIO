package ru.myproject.gamenewsapp.adapters.main

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.myproject.gamenewsapp.adapters.base.BaseUtilItemCallback
import ru.myproject.gamenewsapp.model.base.ListItem

class GamesHorizontalAdapter: AsyncListDifferDelegationAdapter<ListItem>(BaseUtilItemCallback()) {
  init {
    delegatesManager.addDelegate(MainScreenDelegates.wideGameDelegate)
    delegatesManager.addDelegate(MainScreenDelegates.thinGameDelegate)
    delegatesManager.addDelegate(MainScreenDelegates.wideProgressDelegate)
    delegatesManager.addDelegate(MainScreenDelegates.thinProgressDelegate)
  }
}