package ru.myproject.gamenewsapp

import android.app.Application
import ru.myproject.gamenewsapp.di.DaggerAppComponent

class App: Application() {

  override fun onCreate() {
    super.onCreate()
    initDi()

  }

  private fun initDi(){
    DI.appComponent=DaggerAppComponent.builder()
      .appContext(this)
      .build()

  }
}