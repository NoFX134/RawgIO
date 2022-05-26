package ru.myproject.gamenewsapp.viewmodel.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import ru.myproject.gamenewsapp.DI
import ru.myproject.gamenewsapp.di.ScreenScope
import ru.myproject.gamenewsapp.di.ViewModelFactory
import ru.myproject.gamenewsapp.di.ViewModelKey
import ru.myproject.gamenewsapp.util.ResourceProvider


@Component(modules = [MainScreenModule::class])
@ScreenScope
interface MainScreenComponent {

  fun viewModelFactory(): ViewModelFactory

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun resources(resourceProvider: ResourceProvider): Builder

    fun build(): MainScreenComponent
  }

  companion object {
    fun create() = with(DI.appComponent) {
      DaggerMainScreenComponent.builder()
        .resources(resources())
        .build()
    }
  }
}

@Module
abstract class MainScreenModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainScreenViewModel::class)
  abstract fun mainScreenViewModel(viewModel: MainScreenViewModel): ViewModel

}