package ru.myproject.gamenewsapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import ru.myproject.gameNewsAPP.R
import ru.myproject.gameNewsAPP.databinding.ActivityMainBinding


@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding= ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    if(savedInstanceState==null){
      supportFragmentManager.beginTransaction()
        .add(R.id.container, MainFragment())
        .commitAllowingStateLoss()
    }
  }
}