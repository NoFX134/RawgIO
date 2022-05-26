package ru.myproject.gamenewsapp.di

import android.content.Context
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import ru.myproject.gamenewsapp.util.ResourceProvider
import ru.myproject.gamenewsapp.util.ResourceProviderImpl
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

  fun resources(): ResourceProvider

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun appContext(context: Context): Builder

    fun build(): AppComponent
  }
}

@Module
abstract class AppModule {
  @Binds
  @Singleton
  abstract fun bindResourceProvider(provider: ResourceProviderImpl): ResourceProvider
}