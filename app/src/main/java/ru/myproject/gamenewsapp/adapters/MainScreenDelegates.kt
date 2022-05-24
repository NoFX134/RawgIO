package ru.myproject.gamenewsapp.adapters

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.myproject.gameNewsAPP.databinding.ItemGameThinBinding
import ru.myproject.gameNewsAPP.databinding.ItemGameWideBinding
import ru.myproject.gameNewsAPP.databinding.ItemGamesHorizontalBinding
import ru.myproject.gamenewsapp.ui.base.ListItem
import ru.myproject.gamenewsapp.ui.main.GameThinItem
import ru.myproject.gamenewsapp.ui.main.GameWideItem

object MainScreenDelegates {

  val gamesHorizontalDelegate =
    adapterDelegateViewBinding<GamesHorizontalItem, ListItem, ItemGamesHorizontalBinding>(
      { inflater, container ->
        ItemGamesHorizontalBinding.inflate(inflater, container, false)
      }
    ) {
      binding.rvRecycleView.adapter = ListDelegationAdapter(
        wideGameDelegate,
        thinGameDelegate
      )
      bind {
        binding.tvTitle.text = item.title
        (binding.rvRecycleView.adapter as ListDelegationAdapter<List<ListItem>>).apply {
          items = item.games
        }
      }
    }
  private val wideGameDelegate =
    adapterDelegateViewBinding<GameWideItem, ListItem, ItemGameWideBinding>(
      { inflater, container -> ItemGameWideBinding.inflate(inflater, container, false) }
    ) {
      bind {
        binding.ivImageView.setBackgroundColor(item.title.hashCode())
        binding.title = item.title
        binding.executePendingBindings()
      }
    }
  private val thinGameDelegate =
    adapterDelegateViewBinding<GameThinItem, ListItem, ItemGameThinBinding>(
      { inflater, container -> ItemGameThinBinding.inflate(inflater, container, false) }
    ) {
      bind {
        binding.ivImageView.setBackgroundColor(item.title.hashCode())
        binding.title = item.title
        binding.executePendingBindings()
      }
    }

}