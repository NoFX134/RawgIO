package ru.myproject.gamenewsapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.myproject.gameNewsAPP.R
import ru.myproject.gameNewsAPP.databinding.FragmentMainBinding
import ru.myproject.gamenewsapp.ui.base.base.viewBinding

class MainFragment : Fragment(R.layout.fragment_main) {
  private val binding by viewBinding { FragmentMainBinding.bind(it) }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

  }



}

