package ru.myproject.gamenewsapp.network.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.myproject.gameNewsAPP.BuildConfig
import ru.myproject.gamenewsapp.network.api.RawgApi
import ru.myproject.gamenewsapp.util.Constants.BASE_URL

interface NetworkComponent {
  companion object {

    val retrofit: RawgApi by lazy {
      val logging = HttpLoggingInterceptor()
      if (BuildConfig.DEBUG) logging.setLevel(HttpLoggingInterceptor.Level.BODY)
      else logging.setLevel(HttpLoggingInterceptor.Level.NONE)
      val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
      Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(RawgApi::class.java)
    }
  }
}