package ru.myproject.gamenewsapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.myproject.gameNewsAPP.R
import ru.myproject.gameNewsAPP.databinding.FragmentMainBinding
import ru.myproject.gamenewsapp.ui.base.ListItem
import ru.myproject.gamenewsapp.ui.base.viewBinding
import ru.myproject.gamenewsapp.ui.main.MainScreenDelegates.gamesHorizontalDelegate

class MainFragment : Fragment(R.layout.fragment_main) {
  private val binding by viewBinding { FragmentMainBinding.bind(it) }
  private val adapter =
    ListDelegationAdapter(gamesHorizontalDelegate)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(binding) {
      rvRecycleView.adapter = adapter
      adapter.apply {
        items = listOf(
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
        notifyDataSetChanged()
      }
    }
  }
}
