package br.com.spacexlaunches.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import br.com.spacexlaunches.base.api.SpaceXRepository
import kotlinx.coroutines.Dispatchers
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

    val listViewState: LiveData<ListViewState> = liveData(Dispatchers.IO) {
        try {

            val launches = repository.getAllLaunches()
            emit(ListViewState.Success(launches))
        } catch (exception: HttpException) {
            emit(ListViewState.Error(exception.message()))
        }
    }

}
