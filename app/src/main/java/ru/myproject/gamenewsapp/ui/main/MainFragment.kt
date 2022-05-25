package ru.myproject.gamenewsapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.myproject.gameNewsAPP.R
import ru.myproject.gameNewsAPP.databinding.FragmentMainBinding
import ru.myproject.gamenewsapp.adapters.main.MainScreenAdapter
import ru.myproject.gamenewsapp.adapters.main.MainScreenDelegates.gamesHorizontalDelegate
import ru.myproject.gamenewsapp.ui.base.viewBinding
import ru.myproject.gamenewsapp.viewmodel.main.MainScreenViewModel

class MainFragment : Fragment(R.layout.fragment_main) {
  private val binding by viewBinding { FragmentMainBinding.bind(it) }
  private val adapter = MainScreenAdapter()
  private val viewModel by viewModels<MainScreenViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(binding) {
      rvRecycleView.adapter = adapter
      viewModel.data.observe(viewLifecycleOwner) {
        adapter.apply {
          items = it
        }
      }
    }
  }
}
