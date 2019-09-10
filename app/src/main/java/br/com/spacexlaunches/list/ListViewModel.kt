package br.com.spacexlaunches.list

import androidx.lifecycle.*
import br.com.spacexlaunches.base.api.SpaceXRepository
import br.com.spacexlaunches.base.api.models.Launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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

    private val listViewState: MutableLiveData<ListViewState> = MutableLiveData()
    private var job: Job? = null
    private var currentLaunchList: List<Launch> = emptyList()

    private var loadingForTheFirstTime = true

    fun getListViewState(): LiveData<ListViewState> = listViewState

    fun getAllLaunches() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            postLoading()
            while (true) {
                try {
                    handleResult(repository.getAllLaunches())
                } catch (exception: HttpException) {
                    postError()
                }
                delay(DELAY_TIME_IN_MILLIS)
            }
        }
    }

    private fun handleResult(newLaunchList: List<Launch>) {
        when {
            newLaunchList.isEmpty() -> postEmpty()
            newLaunchList.size < currentLaunchList.size -> postUpdateToList(newLaunchList)
            currentLaunchList.isEmpty() -> postUpdateToList(newLaunchList)
            currentLaunchList != newLaunchList -> postAppendToList(
                getDifferentElements(newLaunchList).asReversed()
            )
        }
        currentLaunchList = newLaunchList
    }

    // Private methods

    private fun postLoading() {
        if (loadingForTheFirstTime) {
            loadingForTheFirstTime = false
            listViewState.postValue(ListViewState.FirstTimeLoading)
        } else {
            listViewState.postValue(ListViewState.DefaultLoading)
        }
    }

    private fun postError() {
        listViewState.postValue(ListViewState.Error)
    }

    private fun postEmpty() {
        listViewState.postValue(ListViewState.Empty)
    }

    private fun postUpdateToList(newLaunchList: List<Launch>) {
        listViewState.postValue(ListViewState.UpdateList(newLaunchList.asReversed()))
    }

    private fun postAppendToList(differentElements: List<Launch>) {
        listViewState.postValue(ListViewState.AppendToList(differentElements.asReversed()))
    }

    private fun getDifferentElements(newLaunchList: List<Launch>): List<Launch> {
        return newLaunchList.toSet().minus(currentLaunchList.toSet()).toList()
    }

    companion object {
        private const val DELAY_TIME_IN_MILLIS: Long = 10000
    }

}
