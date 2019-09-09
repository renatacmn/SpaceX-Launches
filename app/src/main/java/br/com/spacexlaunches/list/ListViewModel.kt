package br.com.spacexlaunches.list

import androidx.lifecycle.*
import br.com.spacexlaunches.base.api.SpaceXRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class ListViewModelFactory @Inject constructor(
    private val repository: SpaceXRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(repository) as T
    }
}

class ListViewModel(
    private val repository: SpaceXRepository
) : ViewModel() {

    private var loadingForTheFirstTime = true

    private val listViewState: MutableLiveData<ListViewState> = MutableLiveData()

    fun getListViewState(): LiveData<ListViewState> = listViewState

    fun getAllLaunches() {
        viewModelScope.launch {
            try {
                postValueLoading()
                val launches = repository.getAllLaunches()
                listViewState.postValue(ListViewState.Success(launches))
            } catch (exception: HttpException) {
                listViewState.postValue(ListViewState.Error(exception.message()))
            }
        }
    }

    // Private methods

    private fun postValueLoading() {
        if (loadingForTheFirstTime) {
            loadingForTheFirstTime = false
            listViewState.postValue(ListViewState.FirstTimeLoading)
        } else {
            listViewState.postValue(ListViewState.DefaultLoading)
        }
    }

}
