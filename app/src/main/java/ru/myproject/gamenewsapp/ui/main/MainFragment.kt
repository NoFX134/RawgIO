package ru.myproject.gamenewsapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import ru.myproject.gameNewsAPP.R
import ru.myproject.gameNewsAPP.databinding.FragmentMainBinding
import ru.myproject.gamenewsapp.adapters.main.MainScreenAdapter
import ru.myproject.gamenewsapp.ui.base.viewBinding
import ru.myproject.gamenewsapp.viewmodel.main.MainScreenComponent
import ru.myproject.gamenewsapp.viewmodel.main.MainScreenViewModel

@FlowPreview
@ExperimentalCoroutinesApi
class MainFragment : Fragment(R.layout.fragment_main) {
  private val component by lazy {  MainScreenComponent.create()
  }
  private val binding by viewBinding { FragmentMainBinding.bind(it) }
  private val viewModel by viewModels<MainScreenViewModel> { component.viewModelFactory() }
  private val adapter by lazy { MainScreenAdapter(onItemBind = viewModel::initCategory) }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(binding) {
      rvRecycleView.adapter = adapter
      viewModel.data.observe(viewLifecycleOwner) {
        adapter.items = it
        }
      }
    }
  }

