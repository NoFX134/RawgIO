package ru.myproject.gamenewsapp.network.di

import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.myproject.gameNewsAPP.BuildConfig
import ru.myproject.gamenewsapp.network.api.RawgApi
import ru.myproject.gamenewsapp.util.Constants
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface NetworkComponent {
  fun api(): RawgApi
}

@Module
abstract class NetworkModule {

  companion object {
    @Provides
    @Singleton
    fun provideApi(): RawgApi {
      val logging = HttpLoggingInterceptor()
      if (BuildConfig.DEBUG) logging.setLevel(HttpLoggingInterceptor.Level.BODY)
      else logging.setLevel(HttpLoggingInterceptor.Level.NONE)
      val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
      return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(RawgApi::class.java)
    }
  }
}

