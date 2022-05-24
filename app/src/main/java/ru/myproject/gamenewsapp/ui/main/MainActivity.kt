package ru.myproject.gamenewsapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.myproject.gameNewsAPP.R
import ru.myproject.gameNewsAPP.databinding.ActivityMainBinding
import ru.myproject.gamenewsapp.ui.main.MainFragment


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