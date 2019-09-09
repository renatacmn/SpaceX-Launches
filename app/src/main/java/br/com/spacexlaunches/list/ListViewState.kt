package br.com.spacexlaunches.list

import br.com.spacexlaunches.base.api.models.Launch

sealed class ListViewState {

    object FirstTimeLoading : ListViewState()

    object DefaultLoading : ListViewState()

    object Error : ListViewState()

    object Empty : ListViewState()

    class Success(val launches: List<Launch>) : ListViewState()

}