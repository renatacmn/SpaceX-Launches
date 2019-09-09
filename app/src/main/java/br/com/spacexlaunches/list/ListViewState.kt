package br.com.spacexlaunches.list

import br.com.spacexlaunches.base.api.models.Launch

sealed class ListViewState {

    object Loading : ListViewState()

    class Error(val message: String?) : ListViewState()

    object Empty : ListViewState()

    class Success(val launches: List<Launch>) : ListViewState()

}