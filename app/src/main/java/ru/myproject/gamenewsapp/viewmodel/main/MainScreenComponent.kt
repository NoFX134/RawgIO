package ru.myproject.gamenewsapp.viewmodel.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import ru.myproject.gamenewsapp.DI
import ru.myproject.gamenewsapp.di.ScreenScope
import ru.myproject.gamenewsapp.di.ViewModelFactory
import ru.myproject.gamenewsapp.di.ViewModelKey
import ru.myproject.gamenewsapp.interactor.main.MainScreenInteractor
import ru.myproject.gamenewsapp.interactor.main.MainScreenInteractorImpl
import ru.myproject.gamenewsapp.network.api.RawgApi
import ru.myproject.gamenewsapp.util.ResourceProvider


@ExperimentalCoroutinesApi
@FlowPreview
@Component(modules = [MainScreenModule::class])
@ScreenScope
interface MainScreenComponent {

  fun viewModelFactory(): ViewModelFactory

  @Component.Builder
  interface Builder {

    @BindsInstance

    fun resources(resourceProvider: ResourceProvider): Builder

    @BindsInstance
    fun api(api:RawgApi): Builder

    fun build(): MainScreenComponent
  }

  companion object {
    fun create() = with(DI.appComponent) {
      DaggerMainScreenComponent.builder()
        .resources(resources())
        .api(DI.networkComponent.api())
        .build()
    }
  }
}

@FlowPreview
@ExperimentalCoroutinesApi
@Module
abstract class MainScreenModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainScreenViewModel::class)
  abstract fun mainScreenViewModel(viewModel: MainScreenViewModel): ViewModel

  @Binds
  @ScreenScope
  abstract fun mainScreenInteractor(interactor: MainScreenInteractorImpl): MainScreenInteractor
}