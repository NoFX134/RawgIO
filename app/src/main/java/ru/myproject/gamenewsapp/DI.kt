package ru.myproject.gamenewsapp

import ru.myproject.gamenewsapp.di.AppComponent
import ru.myproject.gamenewsapp.network.di.NetworkComponent

object DI {
  lateinit var appComponent: AppComponent
  internal set

  lateinit var networkComponent: NetworkComponent
  internal set
}