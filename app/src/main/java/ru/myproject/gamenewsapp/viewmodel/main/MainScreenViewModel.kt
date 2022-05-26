package ru.myproject.gamenewsapp.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.myproject.gamenewsapp.interactor.main.MainScreenInteractor
import ru.myproject.gamenewsapp.interactor.main.MainScreenInteractorImpl
import ru.myproject.gamenewsapp.model.base.ListItem
import ru.myproject.gamenewsapp.util.ResourceProvider
import ru.myproject.gamenewsapp.viewmodel.base.BaseViewModel
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class MainScreenViewModel @Inject constructor(
  private val resources: ResourceProvider,
  private val interactor: MainScreenInteractor
) : BaseViewModel() {

  private val _data = MutableLiveData<List<ListItem>>()
  val data: LiveData<List<ListItem>> = _data

  init {
    viewModelScope.launch(Dispatchers.IO) {
      interactor.data().collect { _data.postValue(it) }
    }
  }
}


