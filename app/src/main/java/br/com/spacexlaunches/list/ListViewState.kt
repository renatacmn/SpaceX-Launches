package br.com.spacexlaunches.list

import br.com.spacexlaunches.base.api.models.Launch

sealed class ListViewState {

    object FirstTimeLoading : ListViewState()

    object DefaultLoading : ListViewState()

    object HideLoading : ListViewState()

    object Error : ListViewState()

    object Empty : ListViewState()

    class UpdateList(val launches: List<Launch>) : ListViewState()

    class AppendToList(val newLaunchesToAppend: List<Launch>) : ListViewState()

}